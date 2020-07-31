package cn.weidea.wesports.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVo implements Serializable {

    private static final long serialVersionUID = 8419526607958544447L;

    @NotNull
    @Size(message = "用户名为空")
    private String username;

    @NotNull
    @Size(message = "密码为空")
    private String password;

    @NotNull
    @Size(message = "群组id为空")
    private String groupId;
}
