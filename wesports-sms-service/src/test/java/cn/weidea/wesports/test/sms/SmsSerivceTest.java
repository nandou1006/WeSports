package cn.weidea.wesports.test.sms;

import cn.weidea.wesports.entity.CommonResult;
import cn.weidea.wesports.service.impl.SmsServiceApplication;
import cn.weidea.wesports.service.sms.ISmsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = SmsServiceApplication.class)
@ExtendWith(SpringExtension.class)
@Slf4j
public class SmsSerivceTest {

    @Autowired
    ISmsService smsService;

    @Test
    public void test01() {
        CommonResult result = smsService.sendSmsMsg("测试", "13104884636");
        log.info("短信发送结果：{}", result);
    }
}
