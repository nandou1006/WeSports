package cn.weidea.wesports.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class FaceImgUploadVo implements Serializable {
    private String imgUrl;

}
