package cn.weidea.wesports.controller.login;

import cn.weidea.wesports.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RootController {

    @PostMapping(value = "/api/root/login")
    public Object login(@RequestBody LoginVo loginVo) {
        log.info("类别为:{}, 用户{} 登录", loginVo.getGroupId(), loginVo.getUsername());

    }
}
