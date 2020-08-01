package cn.weidea.wesports.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo implements Serializable {

    private static final long serialVersionUID = 5750429207177235012L;

    private String userId;

    private String name;

    private String sex;

    private String profile;

    private int age;

    private int point;

    private String icon;

    private BigDecimal balance;

    private String remark;
}
