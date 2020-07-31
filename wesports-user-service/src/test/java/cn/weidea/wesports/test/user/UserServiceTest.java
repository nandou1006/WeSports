package cn.weidea.wesports.test.user;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.entity.UserInfo;
import cn.weidea.wesports.service.UserServiceApplication;
import cn.weidea.wesports.service.common.login.ILoginService;
import cn.weidea.wesports.service.user.IUserService;
import cn.weidea.wesports.vo.UserInfoVo;
import cn.weidea.wesports.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = UserServiceApplication.class)
@ExtendWith(SpringExtension.class)
@Slf4j
public class UserServiceTest {

    @Autowired
    IUserService userService;

    @Test
    public void test01() {
        CommonResult user = userService.getUserByUserId("3");
        log.info("{}", user);
    }

    @Test
    public void test02() {
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUserId("3");
        userInfoVo.setAge(30);
        userInfoVo.setPoint(123);
        CommonResult result = userService.updateUserData(userInfoVo);
        log.info("{}", result);
    }

    @Test
    public void test03() {
        UserVo userVo = new UserVo();
        userVo.setUsername("asd");
        userVo.setPassword("asd");
        CommonResult commonResult = userService.insertUser(userVo);
        log.info("{}", commonResult);
    }


}
