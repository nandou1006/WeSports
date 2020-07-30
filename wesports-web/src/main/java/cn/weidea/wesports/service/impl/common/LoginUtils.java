package cn.weidea.wesports.service.impl.common;

import cn.weidea.wesports.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录工具类
 *
 * @author L99
 */
@Slf4j
public class LoginUtils {

    /**
     * 判断是否登录
     *
     * @param request
     * @param response
     * @param redisUtils
     * @return
     */
    public static boolean hasLogin(HttpServletRequest request, HttpServletResponse response, RedisUtils redisUtils) {
        String token = CookieUtils.getCookieValue(request, "token");
        if (StringUtils.isEmpty(token)) {
            // 用户没有 cookie，没有登录，返回值为null
            return false;
        }
        // 判断登录状态是否正确
        boolean correctLogin = correctLogin(token, redisUtils);
        if (correctLogin) {
            // 判断是否需要续期并自动续期
            renew(request, response, "token", token, redisUtils);
            return true;
        }
        return false;
    }

    private static boolean correctLogin(String token, RedisUtils redisUtils) {
        boolean hasKey = redisUtils.hasKey(token);
        return hasKey;
    }

    private static void renew(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, RedisUtils redisUtils) {
        Integer currAge = CookieUtils.getMaxAge(request, cookieName);
        if (currAge == null) {
            return;
        }
        // 小于10分钟时进行续期
        if (currAge.intValue() > 0 && currAge.intValue() < 600) {
            // 进行续期
            log.info("当前登录剩余时间:{}, 对token为{}的用户自动续期", currAge.intValue(), cookieValue);
            CookieUtils.setMaxAge(request, response, cookieName, cookieValue, 7200);
            redisUtils.expire(cookieValue, 7200);
        }
    }

    /**
     * 返回用户表示，只有经过拦截器的类能直接调用
     *
     * @param request
     * @return
     */
    public static String getUserId(HttpServletRequest request, RedisUtils redisUtils) {
        String token = CookieUtils.getCookieValue(request, "token");
        return token == null ? null : (String) redisUtils.get(token);
    }

}
