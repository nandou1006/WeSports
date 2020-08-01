package cn.weidea.wesports.web.controller.face;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.enums.ErrorCodeEnum;
import cn.weidea.wesports.service.face.IFaceService;
import cn.weidea.wesports.utils.FtpUtils;
import cn.weidea.wesports.vo.FaceImgUploadVo;
import cn.weidea.wesports.vo.FaceValidationVo;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
public class FaceValidationController {

    @Reference(version = "${wesports.service.version}")
    private IFaceService faceService;

    private static final Logger logger = LoggerFactory.getLogger(FaceValidationController.class);

//    @PostMapping(value = "/face/validationtest")
//    public Object FaceValidation(HttpServletRequest request, @RequestParam(value = "fileData", required = false) MultipartFile multipartFile,@RequestParam(value = "companyId", required = false)String companyId) throws IOException {
//        logger.info("FaceImgUpload Controller");
//        byte[] pic = multipartFile.getBytes();
//        return faceService.FaceValidation(pic,companyId);
//
//    }

    /**
     * @param request
     * @param multipartFile 人脸图像数据
     * @param companyId     商户Id
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/face/validation")
    public Object FaceValidation(HttpServletRequest request, @RequestParam(value = "fileData", required = false) MultipartFile multipartFile, @RequestParam(value = "companyId", required = false) String companyId) throws IOException {
        logger.info("FaceImgUpload Controller");
        String basePath = request.getServletContext().getRealPath("/");

        byte[] pic = multipartFile.getBytes();
        log.info("{}", faceService);
        return faceService.FaceValidation(pic, companyId);

    }

}
