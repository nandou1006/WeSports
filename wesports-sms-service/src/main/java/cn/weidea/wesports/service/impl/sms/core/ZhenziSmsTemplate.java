package cn.weidea.wesports.service.impl.sms.core;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.util.Assert;



public class ZhenziSmsTemplate extends AbstractSmsTemplate {

    @Override
    public boolean execute() throws Exception {
        Assert.notNull(params, "短信发送内容集合不能为空");
        ZhenziSmsClient client = new ZhenziSmsClient(smsConfig.getAPI_URL(), smsConfig.getAPI_ID(), smsConfig.getAPP_SECRET());
        String result = client.send(params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.getIntValue("code") != 0) {
            return false;
        }
        return true;
    }


}
