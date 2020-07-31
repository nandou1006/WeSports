package cn.weidea.wesports.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    //id
    private String id;
    //用户id
    private String userId;

    private String userName;

    private String sex;

    private String profile;

    private String age;

    //积分
    private int points;

    private String iconUrl;

    private String balance;

    private String remarks;

}
