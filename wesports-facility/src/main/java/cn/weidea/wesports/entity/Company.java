package cn.weidea.wesports.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("company")
public class Company {

    private int id;

    private String uscc;

    private String name;

    private String password;

    private String profile;

    private String address;

}
