package cn.weidea.wesports.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FaceValidationDTO implements Serializable {

    private String userId;

    private String companyId;

}
