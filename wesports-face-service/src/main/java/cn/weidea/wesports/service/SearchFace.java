package cn.weidea.wesports.service;

import cn.weidea.wesports.entity.AliyunFaceSearchResultDTO;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

import com.aliyuncs.facebody.model.v20191230.*;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;

public class SearchFace {
    public static final String REGIONID = "cn-shanghai";
    public static final String ACESSKEYID = "LTAI4GBAwuCmN2JXd4D4dPK2";
    public static final String SECRET = "ZwX2CGe2FJ9elSaNmE174A3IaaBhm4";
    public static final String ENDPOINT = "https://oss-cn-shanghai.aliyuncs.com";

    public static void main(String[] args) throws Exception {
        //faceSearch();
        img2OSS();
        //String imageStr = "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";
        //GenerateImage(imageStr, "E://企鹅.jpg");

    }

    public static void faceSearch() {
        DefaultProfile profile = DefaultProfile.getProfile(REGIONID,
                ACESSKEYID, SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        SearchFaceRequest request = new SearchFaceRequest();
        request.setRegionId("cn-shanghai");
        request.setDbName("default");
        //request.setImageUrl("https://weideapicture2.oss-cn-shanghai.aliyuncs.com/1_ys.jpg");//ys人脸
        request.setImageUrl("https://weideapicture2.oss-cn-shanghai.aliyuncs.com/1_bl.jpg");//ys人脸
        //request.setImageUrl("https://weideapicture2.oss-cn-shanghai.aliyuncs.com/error.png");//非人脸图片

        request.setLimit(5);

        try {
            SearchFaceResponse response = client.getAcsResponse(request);
            for (SearchFaceResponse.Data.MatchListItem matchListItem : response.getData().getMatchList())
                for (SearchFaceResponse.Data.MatchListItem.FaceItemsItem faceItemsItem : matchListItem.getFaceItems()) {
                    //System.out.println("111"+ new Gson().toJson(faceItemsItem)+"\n");
                    // 人脸数据库中匹配得到的用户标识
                    String userId = faceItemsItem.getEntityId();

                    //人脸匹配得分
                    double score = faceItemsItem.getScore();
                    System.out.println("userId: " + userId);
                    System.out.println("score: " + score);
                }

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }

    /**
     * 讲
     */
    public static void img2OSS() throws URISyntaxException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //String endpoint = "http://oss-cn-beijing.aliyuncs.com";

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

        ossClient.putObject(bucketName, objectName, file, meta);
        // 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        url.getPath();

        ossClient.shutdown();
        //TODO 分隔符
        System.out.println(url.getProtocol()+"://"+url.getHost()+url.getPath().toString());
        System.out.println(url.toString());
        System.out.println(url.toURI());
    }

    public static File base64ToFile(String base64) {
        if(base64==null||"".equals(base64)) {
            return null;
        }
        byte[] buff=Base64.getDecoder().decode(base64);
        File file=null;
        FileOutputStream fout=null;
        try {
            file = File.createTempFile("tmp", null);
            fout=new FileOutputStream(file);
            fout.write(buff);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fout!=null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }


    public static boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
        if (imgData == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgData);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }



}

