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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
public class FaceValidationController{

    @Autowired
    private  FaceService faceService;

    private static final Logger logger = LoggerFactory.getLogger(FaceValidationController.class);

    @GetMapping (value="/api/face/validation")
    public Object FaceValidation (@RequestBody FaceValidationVo vvo){
        logger.info("FaceValidation Controller");
        return faceService.FaceValidation(vvo);
    }

    /**
     * 接收前端formdata，构造临时文件，并上传到阿里云oss,返回url
     * @param request
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/api/fileupload")
    public Object FaceImgUpload(HttpServletRequest request, @RequestParam(value = "fileData", required = false) MultipartFile multipartFile) throws IOException {
        logger.info("FaceImgUpload Controller");

        String name = multipartFile.getOriginalFilename();
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//文件保存进来，我给他重新命名，数据库保存有原本的名字，所以输出的时候再把他附上原本的名字就行了。
        String filepath = request.getServletContext().getRealPath("/") + "files\\";//获取项目路径到webapp
        File file = new File(filepath);
        if (!file.exists()) {//目录不存在就创建
            file.mkdirs();
        }
        log.info(file.getAbsolutePath());
        try{
            multipartFile.transferTo(new File(file + "\\" + fileName + "." + "jpg"));//保存文件
        }catch(IOException e){
            return CommonResult.failure(ErrorCodeEnum.IMGUPLOADFAIL);
        }
        String imgUrl = file.getAbsolutePath();
        String ossImgUrl = faceService.FaceImgUpload(imgUrl);
        if("".equals(ossImgUrl)){
            return CommonResult.success(ossImgUrl);
        }else{
            return CommonResult.failure(ErrorCodeEnum.IMGUPLOADFAIL);
        }

    }
}
