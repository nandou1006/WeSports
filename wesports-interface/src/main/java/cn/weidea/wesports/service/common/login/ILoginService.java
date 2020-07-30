package cn.weidea.wesports.service.common.login;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.vo.LoginVo;

public interface ILoginService {

    CommonResult login(LoginVo loginVo);
}
