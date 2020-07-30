package cn.weidea.wesports.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVo implements Serializable {

    private static final long serialVersionUID = 8419526607958544447L;

    private String userName;

    private String password;

    private String groupId;
}
