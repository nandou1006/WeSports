package cn.weidea.wesports.service.imp;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.entity.FaceImg2FaceDBDTO;
import cn.weidea.wesports.entity.FaceImgUploadDTO;
import cn.weidea.wesports.entity.FaceValidationDTO;
import cn.weidea.wesports.enums.ErrorCodeEnum;
import cn.weidea.wesports.service.FaceService;
import cn.weidea.wesports.vo.FaceImg2FaceDBVo;
import cn.weidea.wesports.vo.FaceImgUploadVo;
import cn.weidea.wesports.vo.FaceValidationVo;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.facebody.model.v20191230.SearchFaceRequest;
import com.aliyuncs.facebody.model.v20191230.SearchFaceResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Exceptions;

import java.io.File;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FaceServiceImp implements FaceService {
    private static final Logger logger = LoggerFactory.getLogger(FaceServiceImp.class);
    //Hard Code
    private static final String REGIONID = "cn-shanghai";
    private static final String ACESSKEYID = "LTAI4GBAwuCmN2JXd4D4dPK2";
    private static final String SECRET = "ZwX2CGe2FJ9elSaNmE174A3IaaBhm4";
    private static final String ENDPOINT = "https://oss-cn-shanghai.aliyuncs.com";
    /**
     *
     * @param faceValidationVo
     * @return
     */
    @Override
    public CommonResult FaceValidation(FaceValidationVo faceValidationVo) {
        FaceValidationDTO faceValidationDTO = new FaceValidationDTO();
        //String imgUrl = "https://weideapicture2.oss-cn-shanghai.aliyuncs.com/1_ys.jpg";
        String imgUrl = faceValidationVo.getImgURL();
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai",
                "LTAI4GBAwuCmN2JXd4D4dPK2", "ZwX2CGe2FJ9elSaNmE174A3IaaBhm4");
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
            for(SearchFaceResponse.Data.MatchListItem matchListItem:response.getData().getMatchList())
                for(SearchFaceResponse.Data.MatchListItem.FaceItemsItem faceItemsItem:matchListItem.getFaceItems()){
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
            logger.error("ClientException. ",e);
            faceValidationDTO.setUserId("");
            return CommonResult.failure(ErrorCodeEnum.FACENOTEXIST);
        } catch (ClientException e) {
            logger.info("ErrCode:" + e.getErrCode());
            logger.info("ErrMsg:" + e.getErrMsg());
            logger.info("RequestId:" + e.getRequestId());
            logger.error("ClientException. ",e);
            return CommonResult.failure(ErrorCodeEnum.FACENOTEXIST);
        }
    }





    @Override
    public String FaceImgUpload(String imgUrl) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String fileName = "C:\\Users\\nkyang\\Downloads\\photo4.jfif";
        String bucketName = "weideapicture2";
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
        try{
            ossClient.putObject(bucketName, objectName, file, meta);
            // 设置URL过期时间为1小时。
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            ossClient.shutdown();
            ossImgUrl = url.getProtocol()+"://"+url.getHost()+url.getPath().toString();
            System.out.println(url.toString());
        }catch (Exception e){
            return "";
        }

        return ossImgUrl;
    }

    @Override
    public CommonResult FaceImg2FaceDB(FaceImg2FaceDBVo faceImg2FaceDBVO) {
        return null;
    }
}
