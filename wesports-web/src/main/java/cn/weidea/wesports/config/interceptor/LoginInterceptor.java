package cn.weidea.wesports.config.interceptor;

import cn.weidea.wesports.common.LoginUtils;
import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.redis.RedisUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 拦截器配置
 *
 * @author L99
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 检查登录状态
        boolean hasLogin = LoginUtils.hasLogin(request, response, redisUtils);

        if (!hasLogin) {
            constructUnLoginResponse(response, CommonResult.failure(400, "该用户不存在"));
            return false;
        }

        return true;
    }


    /**
     * 返回请求拦截后的响应
     *
     * @param response
     * @param object   响应信息
     */
    private void constructUnLoginResponse(HttpServletResponse response, Object object) {
        String content = JSONObject.toJSONString(object);
        try {
            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception e) {
            log.error("返回信息出错,{}", e);
        }
    }
}
