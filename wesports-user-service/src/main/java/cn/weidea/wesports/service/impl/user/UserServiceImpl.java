package cn.weidea.wesports.service.impl.user;

import cn.weidea.wesports.config.CustomIdGenerator;
import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.entity.User;
import cn.weidea.wesports.entity.UserInfo;
import cn.weidea.wesports.enums.ErrorCodeEnum;
import cn.weidea.wesports.mapper.UserInfoMapper;
import cn.weidea.wesports.mapper.UserMapper;
import cn.weidea.wesports.service.user.IUserService;
import cn.weidea.wesports.vo.UserInfoVo;
import cn.weidea.wesports.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Service(version = "${sports.service.version}")
@Component
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdentifierGenerator customIdGenerator;

    @Override
    public CommonResult getUserByUserId(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        UserInfo userInfo = (UserInfo) userInfoMapper.selectByMap(map);
        return CommonResult.success(userInfo);
    }

    @Override
    public CommonResult updateUserData(UserInfoVo userInfoVo) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoVo, userInfo);
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userInfoVo.getUserId());
        queryWrapper.allEq(map);
        int result = userInfoMapper.update(userInfo, queryWrapper);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failure(ErrorCodeEnum.UPDATE_PERSONAL_DATA_FAIL);
    }

    @Override
    public CommonResult insertUser(UserVo userVo) {
        User user = new User();
        // 创建主键
        String id = customIdGenerator.nextUUID(userVo);
        userVo.setId(id);
        BeanUtils.copyProperties(userVo, user);
        int result = userMapper.insert(user);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failure(ErrorCodeEnum.REGISTER_USER_FAIL);
    }
}
