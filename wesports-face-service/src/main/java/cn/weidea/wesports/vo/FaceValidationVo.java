package cn.weidea.wesports.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaceValidationVo implements Serializable {

    private String imgURL;

    private String companyId;
}
