package cn.weidea.wesports.test.admin;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.service.WebApplication;
import cn.weidea.wesports.service.common.login.ILoginService;
import cn.weidea.wesports.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = WebApplication.class)
@ExtendWith(SpringExtension.class)
@Slf4j
public class Admin {

    @Autowired
    ILoginService loginService;

    @Test
    public void test() {
        LoginVo loginVo = new LoginVo();
        loginVo.setUserName("123");
        loginVo.setPassword("123");
        CommonResult commonResult = loginService.login(loginVo);
        log.info("{}", commonResult);
    }
}
