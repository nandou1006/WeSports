package cn.weidea.wesports.service.face;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.vo.FaceImg2FaceDBVo;
import cn.weidea.wesports.vo.FaceValidationVo;
import java.io.FileNotFoundException;

public interface IFaceService {
    //验证人脸是否存在人脸数据库中
    CommonResult FaceValidation(byte[] bytes, String companyId);

}
