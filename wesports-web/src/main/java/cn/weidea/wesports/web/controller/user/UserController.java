package cn.weidea.wesports.web.controller.user;

import cn.weidea.wesports.redis.RedisUtils;
import cn.weidea.wesports.service.user.IUserService;
import cn.weidea.wesports.web.service.impl.common.LoginUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class UserController {

    @Reference(version = "${wesports.service.version}")
    private IUserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping(value = "/api/user")
    public Object getPersonalData(HttpServletRequest request) {
        String userId = LoginUtils.getUserId(request, redisUtils);
        return userService.getUserByUserId(userId);
    }
}
