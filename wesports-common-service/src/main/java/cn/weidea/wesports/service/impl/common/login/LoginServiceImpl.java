package cn.weidea.wesports.service.impl.common.login;

import cn.weidea.wesports.entity.Admin;
import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.enums.ErrorCodeEnum;
import cn.weidea.wesports.enums.GroupIdEnum;
import cn.weidea.wesports.mapper.AdminMapper;
import cn.weidea.wesports.redis.RedisUtils;
import cn.weidea.wesports.service.common.login.ILoginService;
import cn.weidea.wesports.utils.TokenUtils;
import cn.weidea.wesports.vo.LoginVo;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录服务层接口实现类
 */
@Service(version = "${wesports.service.version}")
@Component
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private AdminMapper adminMapper;

//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private CompanyMapper companyMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public CommonResult login(LoginVo loginVo) {

        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("username", loginVo.getUserName());
        map.put("password", loginVo.getPassword());
        wrapper.allEq(map);
        List<Admin> user = new ArrayList<>();
        if (GroupIdEnum.A.name().equals(loginVo.getGroupId())) {
            user = adminMapper.selectList(wrapper);
        } else if (GroupIdEnum.B.name().equals(loginVo.getGroupId())) {

        }
        if (user != null && user.size() > 0) {
            // 写入redis
            String token = TokenUtils.generateToken(loginVo.getUserName(), loginVo.getPassword());
            redisUtils.set(token, String.valueOf(user.get(0).getId()), 7200);
            Map<String, String> result = new HashMap<>(2);
            result.put("token", token);
            return CommonResult.success(result);
        }
        return CommonResult.failure(ErrorCodeEnum.MISMATCH);
    }

}
