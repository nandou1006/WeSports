package cn.weidea.wesports.service.impl.sms.core;

import cn.weidea.wesports.service.impl.sms.config.SmsConfig;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSmsTemplate implements SmsTemplate {

    SmsConfig smsConfig;

    Map<String, String> params = new HashMap<>();


    public void config(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;
    }

    public SmsTemplate construtMessage(String message, String phone) {
        params.put("message", message);
        params.put("number", phone);
        return this;
    }
}
