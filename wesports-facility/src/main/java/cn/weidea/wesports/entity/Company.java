package cn.weidea.wesports.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("company")
public class Company implements Serializable {

    private static final long serialVersionUID = 7530724811016284998L;

    private int id;

    private String uscc;

    private String name;

    private String password;

    private String profile;

    private String address;

}
