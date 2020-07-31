package cn.weidea.wesports.controller;
import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.entity.FaceImgUploadDTO;
import cn.weidea.wesports.entity.FaceValidationDTO;
import cn.weidea.wesports.enums.ErrorCodeEnum;
import cn.weidea.wesports.service.FaceService;
import cn.weidea.wesports.vo.FaceImgUploadVo;
import cn.weidea.wesports.vo.FaceValidationVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class FaceValidationController{

    @Autowired
    private  FaceService faceService;

    private static final Logger logger = LoggerFactory.getLogger(FaceValidationController.class);

    @GetMapping (value="/api/face/validation")
    public CommonResult<FaceValidationDTO> FaceValidation (@RequestBody FaceValidationVo vvo){
        logger.info("FaceValidation");
        CommonResult<FaceValidationDTO> result = new CommonResult<>();

        FaceValidationDTO faceValidationDTO = faceService.FaceValidation(vvo);
        result.setData(faceValidationDTO);
        if(faceValidationDTO == null || "".equals(faceValidationDTO.getUserId())){
            result.setErrorCode(ErrorCodeEnum.FACENOTEXIST.getCode());
            result.setErrorMessage(ErrorCodeEnum.FACENOTEXIST.getDesc());
        }else{
            result.setErrorCode(ErrorCodeEnum.SUCCESS.getCode());
            result.setErrorMessage(ErrorCodeEnum.SUCCESS.getDesc());
        }

        return result;
    }
    @PostMapping(value = "/api/fileupload")
    public CommonResult<FaceImgUploadDTO> FaceImgUpload(@RequestBody FaceImgUploadVo fvo){
        logger.info("FaceImgUpload");
        CommonResult<FaceImgUploadDTO> result = new CommonResult<>();

        FaceImgUploadDTO faceImgUploadDTO = faceService.FaceImgUpload(fvo);
        result.setData(faceImgUploadDTO);
        return result;
    }
}
