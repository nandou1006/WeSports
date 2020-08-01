package cn.weidea.wesports.web.controller.face;

import cn.weidea.wesports.service.face.IFaceService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@Slf4j
public class FaceValidationController {

    @Reference(version = "${wesports.service.version}")
    private IFaceService faceService;

    private static final Logger logger = LoggerFactory.getLogger(FaceValidationController.class);

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
