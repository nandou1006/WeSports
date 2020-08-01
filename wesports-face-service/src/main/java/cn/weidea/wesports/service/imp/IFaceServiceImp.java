package cn.weidea.wesports.service.imp;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.dto.FaceValidationDTO;
import cn.weidea.wesports.enums.ErrorCodeEnum;
import cn.weidea.wesports.service.face.IFaceService;
import cn.weidea.wesports.vo.FaceImg2FaceDBVo;
import cn.weidea.wesports.vo.FaceValidationVo;
import com.alibaba.dubbo.config.annotation.Service;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.facebody.model.v20191230.SearchFaceRequest;
import com.aliyuncs.facebody.model.v20191230.SearchFaceResponse;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Service(version = "${wesports.service.version}")
public class IFaceServiceImp implements IFaceService {

    private static final Logger logger = LoggerFactory.getLogger(IFaceServiceImp.class);
    //Hard Code
    private static final String REGIONID = "cn-shanghai";
    private static final String ACESSKEYID = "LTAI4GBAwuCmN2JXd4D4dPK2";
    private static final String SECRET = "ZwX2CGe2FJ9elSaNmE174A3IaaBhm4";
    private static final String ENDPOINT = "https://oss-cn-shanghai.aliyuncs.com";
    private static final String BUKETNAME = "weideapicture2";

    /**
     * 人脸验证
     * @param bytes
     * @param companyId
     * @return
     */
    @Override
    public CommonResult FaceValidation(byte[] bytes, String companyId) {
        try {
            String localImgUrl = FileUpload(bytes,companyId);
            logger.info("localImgUrl-{}",localImgUrl);
            String ossImgUrl = FaceImg2OSS(localImgUrl);
            logger.info("ossImgUrl-{}",ossImgUrl);
            String userId = FaceSearch(ossImgUrl);
            logger.info("userId-{}",userId);
            FaceValidationDTO faceValidationDTO = new FaceValidationDTO();
            faceValidationDTO.setUserId(userId);
            faceValidationDTO.setCompanyId(companyId);
            if("".equals(userId)){
                return CommonResult.failure(ErrorCodeEnum.FACENOTEXIST);
            }
            return CommonResult.success(faceValidationDTO);
        }catch (Exception e){
            logger.error("FaceValidationService-"+e);
        }
        return CommonResult.failure(ErrorCodeEnum.FACENOTEXIST);
    }

    /**
     * 根据图像url搜索人脸数据库，成功则返回userId
     * @param ossImgUrl 阿里云oss服务器图像url
     * @return
     * @throws FileNotFoundException
     */
    private String FaceSearch(String ossImgUrl) {
        //搜索得到的用户Id
        String userId = "";
        DefaultProfile profile = DefaultProfile.getProfile(REGIONID,
                ACESSKEYID, SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        SearchFaceRequest searchFaceRequest = new SearchFaceRequest();
        searchFaceRequest.setRegionId("cn-shanghai");
        searchFaceRequest.setDbName("default");
        //设置最多返回一个匹配成功结果
        searchFaceRequest.setLimit(1);
        //人脸图像
        searchFaceRequest.setImageUrl(ossImgUrl);
        double THRESHOLD = 0.6;
        double bestScore = 0.0;
        Map<String, Double> map = new HashMap<>();

        try {
            SearchFaceResponse response = client.getAcsResponse(searchFaceRequest);
            //TODO 场景简化为只有单个匹配结果
            for (SearchFaceResponse.Data.MatchListItem matchListItem : response.getData().getMatchList())
                for (SearchFaceResponse.Data.MatchListItem.FaceItemsItem faceItemsItem : matchListItem.getFaceItems()) {
                    //System.out.println("111"+ new Gson().toJson(faceItemsItem)+"\n");
                    // 人脸数据库中匹配得到的用户标识
                    //人脸匹配得分
                    if(bestScore < faceItemsItem.getScore()){
                        bestScore = faceItemsItem.getScore();
                        userId = faceItemsItem.getEntityId();
                    }
                    System.out.println(userId);
                }
            if(bestScore<THRESHOLD)userId = "";
            return userId;
        } catch (ServerException e) {
            e.printStackTrace();
            logger.error("ClientException. ", e);
            return "";
        } catch (ClientException e) {
            logger.info("ErrCode:" + e.getErrCode());
            logger.info("ErrMsg:" + e.getErrMsg());
            logger.info("RequestId:" + e.getRequestId());
            logger.error("ClientException. ", e);
            return "";
        }
    }

    /**
     * 将bytes写入到本地文件
     * @param bytes
     * @param companyId
     * @return
     * @throws FileNotFoundException
     */
    private String FileUpload(byte[] bytes, String companyId) throws FileNotFoundException {
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//文件保存进来，我给他重新命名，数据库保存有原本的名字，所以输出的时候再把他附上原本的名字就行了。

        File file = new File(ResourceUtils.getURL("classpath:").getPath() + fileName + ".jpg");
        logger.info("路径：{}", file.getAbsolutePath());
        if (!file.exists()) {//目录不存在就创建
            //file.mkdirs();
        }
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            bufferedOutputStream.write(bytes);
        } catch (Exception e) {

        }

        logger.info(file.getAbsolutePath());
        return file.getAbsolutePath();
    }

    /**
     * 将本地图像上传到阿里云oss服务器，返回url
     * @param localImgUrl
     * @return
     */
    private String FaceImg2OSS(String localImgUrl) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        // TODO 获取文件的后缀名
        String suffixName = ".jpg";
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = sdf.format(new Date()) + "/" + finalFileName;
        File file = new File(localImgUrl);

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentDisposition("attachment;");
        OSSClient ossClient = new OSSClient(ENDPOINT, ACESSKEYID, SECRET);
        String ossImgUrl = "";
        try {
            ossClient.putObject(BUKETNAME, objectName, file, meta);
            // 设置URL过期时间为1小时。
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            URL url = ossClient.generatePresignedUrl(BUKETNAME, objectName, expiration);
            logger.info(url.toString());
            ossClient.shutdown();
            ossImgUrl = url.getProtocol() + "://" + url.getHost() + url.getPath().toString();
            logger.info(url.toString());
        } catch (Exception e) {
            logger.error("FaceImg2OSS" + e);
        }

        return ossImgUrl;
    }
    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/


    /**
     * @param faceValidationVo
     * @return
     */
    private CommonResult FaceValidationtTest(FaceValidationVo faceValidationVo) {
        FaceValidationDTO faceValidationDTO = new FaceValidationDTO();
        //String imgUrl = "https://weideapicture2.oss-cn-shanghai.aliyuncs.com/1_ys.jpg";
        logger.info("faceValidationVo.ImgURL" + faceValidationVo.getImgUrl());
        String imgUrl = faceValidationVo.getImgUrl();
        DefaultProfile profile = DefaultProfile.getProfile(REGIONID,
                ACESSKEYID, SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        SearchFaceRequest request = new SearchFaceRequest();
        request.setRegionId("cn-shanghai");
        request.setDbName("default");
        //设置最多返回一个匹配成功结果
        request.setLimit(1);
        //人脸图像
        request.setImageUrl(imgUrl);

        try {
            SearchFaceResponse response = client.getAcsResponse(request);
            //TODO 场景简化为只有单个匹配结果
            for (SearchFaceResponse.Data.MatchListItem matchListItem : response.getData().getMatchList())
                for (SearchFaceResponse.Data.MatchListItem.FaceItemsItem faceItemsItem : matchListItem.getFaceItems()) {
                    //System.out.println("111"+ new Gson().toJson(faceItemsItem)+"\n");
                    // 人脸数据库中匹配得到的用户标识
                    String userId = faceItemsItem.getEntityId();
                    //人脸匹配得分
                    double score = faceItemsItem.getScore();
                    System.out.println(userId);
                    faceValidationDTO.setUserId(userId);
                }
            return CommonResult.success(faceValidationDTO);
        } catch (ServerException e) {
            e.printStackTrace();
            logger.error("ClientException. ", e);
            faceValidationDTO.setUserId("");
            return CommonResult.failure(ErrorCodeEnum.FACENOTEXIST);
        } catch (ClientException e) {
            logger.info("ErrCode:" + e.getErrCode());
            logger.info("ErrMsg:" + e.getErrMsg());
            logger.info("RequestId:" + e.getRequestId());
            logger.error("ClientException. ", e);
            return CommonResult.failure(ErrorCodeEnum.FACENOTEXIST);
        }
    }


    private String FileUploadTest(String localImgUrl) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //TODO
        String fileName = "C:\\Users\\nkyang\\Downloads\\photo4.jfif";

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = sdf.format(new Date()) + "/" + finalFileName;
        File file = new File(fileName);

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentDisposition("attachment;");
        OSSClient ossClient = new OSSClient(ENDPOINT, ACESSKEYID, SECRET);
        String ossImgUrl = "";
        try {
            ossClient.putObject(BUKETNAME, objectName, file, meta);
            // 设置URL过期时间为1小时。
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            URL url = ossClient.generatePresignedUrl(BUKETNAME, objectName, expiration);
            ossClient.shutdown();
            ossImgUrl = url.getProtocol() + "://" + url.getHost() + url.getPath().toString();
            System.out.println(url.toString());
        } catch (Exception e) {
            logger.error("FaceImg2OSS" + e);
            return "";
        }

        return ossImgUrl;
    }


    /**
     * 测试方法
     *
     * @param localImgUrl
     * @return
     */
    private String FaceImg2OSSTest(String localImgUrl) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //TODO
        String fileName = "C:\\Users\\nkyang\\Downloads\\photo4.jfif";

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = sdf.format(new Date()) + "/" + finalFileName;
        File file = new File(fileName);

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentDisposition("attachment;");
        OSSClient ossClient = new OSSClient(ENDPOINT, ACESSKEYID, SECRET);
        String ossImgUrl = "";
        try {
            ossClient.putObject(BUKETNAME, objectName, file, meta);
            // 设置URL过期时间为1小时。
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            URL url = ossClient.generatePresignedUrl(BUKETNAME, objectName, expiration);
            ossClient.shutdown();
            ossImgUrl = url.getProtocol() + "://" + url.getHost() + url.getPath().toString();
            System.out.println(url.toString());
        } catch (Exception e) {
            logger.error("FaceImg2OSS" + e);
            return "";
        }

        return ossImgUrl;
    }


    public CommonResult FaceImg2FaceDB(FaceImg2FaceDBVo faceImg2FaceDBVO) {
        return null;
    }
}
