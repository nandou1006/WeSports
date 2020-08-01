package cn.weidea.wesports.service.user;


import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.vo.UserInfoVo;
import cn.weidea.wesports.vo.UserVo;

public interface IUserService {

    /**
     * 获取用户资料
     *
     * @param userId
     * @return
     */
    CommonResult getUserByUserId(String userId);

    /**
     * 更新用户资料
     *
     * @param userInfoVo
     * @return
     */
    CommonResult updateUserData(UserInfoVo userInfoVo);

    /**
     * 新增用户
     *
     * @return
     */
    CommonResult insertUser(UserVo userVo);

}
