package cn.weidea.wesports.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyVo implements Serializable {

    private String uscc;

    private String name;

    private String password;

    private String profile;

    private String address;
}
