package cn.weidea.wesports.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.*;
import com.aliyuncs.facebody.model.v20191230.*;

public class SearchFace {

    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai",
                "LTAI4GBAwuCmN2JXd4D4dPK2", "ZwX2CGe2FJ9elSaNmE174A3IaaBhm4");
        IAcsClient client = new DefaultAcsClient(profile);

        SearchFaceRequest request = new SearchFaceRequest();
        request.setRegionId("cn-shanghai");
        request.setDbName("default");
        //request.setImageUrl("https://weideapicture2.oss-cn-shanghai.aliyuncs.com/1_ys.jpg");//ys人脸
        request.setImageUrl("https://weideapicture2.oss-cn-shanghai.aliyuncs.com/2_ys.jpg");//ys人脸
        //request.setImageUrl("https://weideapicture2.oss-cn-shanghai.aliyuncs.com/error.png");//非人脸图片

        request.setLimit(5);

        try {
            SearchFaceResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }
}

