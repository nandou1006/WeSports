package cn.weidea.wesports.service;

import cn.weidea.wesports.entity.FaceImg2FaceDBDTO;
import cn.weidea.wesports.entity.FaceImgUploadDTO;
import cn.weidea.wesports.entity.FaceValidationDTO;
import cn.weidea.wesports.vo.FaceImg2FaceDBVo;
import cn.weidea.wesports.vo.FaceImgUploadVo;
import cn.weidea.wesports.vo.FaceValidationVo;

public interface FaceService {
    //验证人脸是否存在人脸数据库中
    FaceValidationDTO  FaceValidation(FaceValidationVo faceValidationVo);
    //将人脸图像上传到阿里云服务器
    FaceImgUploadDTO FaceImgUpload(FaceImgUploadVo faceImgUploadVo);
    //在人脸数据库中添加人脸图像数据(阿里云)
    FaceImg2FaceDBDTO FaceImg2FaceDB(FaceImg2FaceDBVo faceImg2FaceDBVO);
}
