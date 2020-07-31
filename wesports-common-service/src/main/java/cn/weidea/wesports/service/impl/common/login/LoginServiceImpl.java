package cn.weidea.wesports.service.impl.common.login;

import cn.weidea.wesports.entity.*;
import cn.weidea.wesports.enums.ErrorCodeEnum;
import cn.weidea.wesports.enums.GroupIdEnum;
import cn.weidea.wesports.mapper.AdminMapper;
import cn.weidea.wesports.mapper.CompanyMapper;
import cn.weidea.wesports.mapper.UserMapper;
import cn.weidea.wesports.redis.RedisUtils;
import cn.weidea.wesports.service.common.login.ILoginService;
import cn.weidea.wesports.utils.TokenUtils;
import cn.weidea.wesports.vo.LoginVo;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录服务层接口实现类
 */
@Service(version = "${wesports.service.version}")
@Component
public class LoginServiceImpl implements ILoginService {

    private final AdminMapper adminMapper;

    private final UserMapper userMapper;

    private final CompanyMapper companyMapper;

    private final RedisUtils redisUtils;

    @Autowired
    public LoginServiceImpl(AdminMapper adminMapper, UserMapper userMapper, CompanyMapper companyMapper, RedisUtils redisUtils) {
        this.adminMapper = adminMapper;
        this.userMapper = userMapper;
        this.companyMapper = companyMapper;
        this.redisUtils = redisUtils;
    }

    @Override
    public CommonResult login(LoginVo loginVo) {

        Map map = constructMapByLoginVo(loginVo);

        // TODO 垃圾代码的味道
        if (GroupIdEnum.A.name().equals(loginVo.getGroupId())) {
            // 管理员
            QueryWrapper<Admin> wrapper = new QueryWrapper<>();
            wrapper.allEq(map);
            List<Admin> admins = adminMapper.selectList(wrapper);
            if (admins != null && admins.size() > 0) {
                // 写入redis
                String token = TokenUtils.generateToken(loginVo.getUserName(), loginVo.getPassword());
                redisUtils.set(token, String.valueOf(admins.get(0).getId()), 7200);
                Map<String, String> result = new HashMap<>(2);
                result.put("token", token);
                return CommonResult.success(result);
            }
        } else if (GroupIdEnum.C.name().equals(loginVo.getGroupId())) {
            // 商家
            QueryWrapper<Company> wrapper = new QueryWrapper<>();
            wrapper.allEq(map);
            List<Company> companies = companyMapper.selectList(wrapper);
            if (companies != null && companies.size() > 0) {
                // 写入redis
                String token = TokenUtils.generateToken(loginVo.getUserName(), loginVo.getPassword());
                redisUtils.set(token, String.valueOf(companies.get(0).getId()), 7200);
                Map<String, String> result = new HashMap<>(2);
                result.put("token", token);
                return CommonResult.success(result);
            }
        } else {
            // 普通用户
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.allEq(map);
            List<User> users = userMapper.selectList(wrapper);
            if (users != null && users.size() > 0) {
                // 写入redis
                String token = TokenUtils.generateToken(loginVo.getUserName(), loginVo.getPassword());
                redisUtils.set(token, String.valueOf(users.get(0).getId()), 7200);
                Map<String, String> result = new HashMap<>(2);
                result.put("token", token);
                return CommonResult.success(result);
            }
        }
        return CommonResult.failure(ErrorCodeEnum.MISMATCH);
    }

    private Map constructMapByLoginVo(LoginVo loginVo) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", loginVo.getUserName());
        map.put("password", loginVo.getPassword());
        return map;
    }

}
