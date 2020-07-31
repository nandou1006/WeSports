package cn.weidea.wesports.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private int id;

    private String name;

    private String sex;

    private String profile;

    private int age;

    private int point;

    private String icon;

    private BigDecimal balance;

    private String remark;
}
