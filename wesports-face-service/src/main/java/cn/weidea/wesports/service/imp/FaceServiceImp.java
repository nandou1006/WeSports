package cn.weidea.wesports.service.imp;

import cn.weidea.wesports.entity.FaceImg2FaceDBDTO;
import cn.weidea.wesports.entity.FaceImgUploadDTO;
import cn.weidea.wesports.entity.FaceValidationDTO;
import cn.weidea.wesports.service.FaceService;
import cn.weidea.wesports.vo.FaceImg2FaceDBVo;
import cn.weidea.wesports.vo.FaceImgUploadVo;
import cn.weidea.wesports.vo.FaceValidationVo;
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

@Service
public class FaceServiceImp implements FaceService {
    private static final Logger logger = LoggerFactory.getLogger(FaceServiceImp.class);

    /**
     *
     * @param faceValidationVo
     * @return
     */
    @Override
    public FaceValidationDTO FaceValidation(FaceValidationVo faceValidationVo) {
        FaceValidationDTO faceValidationDTO = new FaceValidationDTO();
        //String imgUrl = "https://weideapicture2.oss-cn-shanghai.aliyuncs.com/1_ys.jpg";
        String imgUrl = faceValidationVo.getImgURL();
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai",
                "LTAI4GBAwuCmN2JXd4D4dPK2", "ZwX2CGe2FJ9elSaNmE174A3IaaBhm4");
        IAcsClient client = new DefaultAcsClient(profile);

        SearchFaceRequest request = new SearchFaceRequest();
        request.setRegionId("cn-shanghai");
        request.setDbName("default");
        request.setLimit(5);

        request.setImageUrl(imgUrl);//ys人脸

        try {
            SearchFaceResponse response = client.getAcsResponse(request);
            SearchFaceResponse.Data.MatchListItem matchListItem = new SearchFaceResponse.Data.MatchListItem();
            System.out.println(new Gson().toJson(response));
            faceValidationDTO.setUserId(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
            logger.error("ClientException. ",e);
            faceValidationDTO.setUserId("");
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
            logger.error("ClientException. ",e);
            faceValidationDTO.setUserId("");
        }
        logger.info("FaceValidationDTO. ");
        return faceValidationDTO;
    }

    @Override
    public FaceImgUploadDTO FaceImgUpload(FaceImgUploadVo faceImgUploadVo) {
        return null;
    }

    @Override
    public FaceImg2FaceDBDTO FaceImg2FaceDB(FaceImg2FaceDBVo faceImg2FaceDBVO) {
        return null;
    }
}
