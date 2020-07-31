package cn.weidea.wesports.service.sms;

import cn.weidea.wesports.entity.CommonResult;

public interface ISmsService {

    CommonResult sendSmsMsg(String message, String phone);

}
