package cn.weidea.wesports.entity;

import lombok.Data;

@Data
public class CompanyField {

    private int id;

    private int companyId;

    private int type;//场地类型的编号表示

    private int number;

    private String remark;//场地的类型的中文表示

    private int occupy;//

}
