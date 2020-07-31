package cn.weidea.wesports.service.impl.sms;


import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.enums.ErrorCodeEnum;
import cn.weidea.wesports.service.impl.sms.config.SmsConfig;
import cn.weidea.wesports.service.impl.sms.core.ZhenziSmsTemplate;
import cn.weidea.wesports.service.sms.ISmsService;
import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Service(version = "${wesports.service.version}")
@Component
public class SmsServiceImpl implements ISmsService {

    @Autowired
    SmsConfig smsConfig;

    public CommonResult sendSmsMsg(String message, String phone) {

        ZhenziSmsTemplate zhenziSmsTemplate = new ZhenziSmsTemplate();
        zhenziSmsTemplate.config(smsConfig);

        try {
            boolean result = zhenziSmsTemplate.construtMessage(message, phone).execute();
            if (result) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.info("发送短信失败，短信接收方为:{}", phone);
            log.warn(e.getMessage());
        }
        return CommonResult.failure(ErrorCodeEnum.SMS_SEND_FAIL);
    }
}
