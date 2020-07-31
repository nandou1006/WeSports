package cn.weidea.wesports.web.controller.login;

import cn.weidea.wesports.redis.RedisUtils;
import cn.weidea.wesports.service.common.login.ILoginService;
import cn.weidea.wesports.vo.LoginVo;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Reference(version = "${wesports.service.version}")
    private ILoginService loginService;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping(value = "/api/login")
    public Object login(@RequestBody @Validated LoginVo loginVo) {
        log.info("用户:{}请求登录,登录类型为:{}", loginVo.getUsername(), loginVo.getGroupId());
        return loginService.login(loginVo);
    }
}
