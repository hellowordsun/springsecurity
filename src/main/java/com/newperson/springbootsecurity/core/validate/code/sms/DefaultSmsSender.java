package com.newperson.springbootsecurity.core.validate.code.sms;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.google.gson.Gson;
import com.newperson.springbootsecurity.core.properties.SecurityProperties;
import com.newperson.springbootsecurity.core.validate.code.ValidateCode;
import com.newperson.springbootsecurity.demo.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 18:15
 */
@Slf4j
@Component("smsCodeSender")
public class DefaultSmsSender implements SmsCodeSender {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private Gson gson;

    @Override
    public void smsSend(String phone, ValidateCode validateCode) throws Exception {
        int appId = securityProperties.getCode().getSms().getAppID();
        String appKey = securityProperties.getCode().getSms().getAppKey();
        Integer templateId = securityProperties.getCode().getSms().getTemplateId();
        String signName = securityProperties.getCode().getSms().getSignName();
        int expire = securityProperties.getCode().getSms().getExpire();
        String code = validateCode.getCode();
        String [] param={code,String.valueOf(expire)};

        //发送短息你的sdk
        SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
        SmsSingleSenderResult result = ssender.sendWithParam("86", phone, templateId, param, signName, "", "");
        String json = gson.toJson(result);
        JSONObject jsonObject = JSON.parseObject(json);
        int resultInt =(int) jsonObject.get("result");
        if (resultInt!=0) {
            String errmsg =(String) jsonObject.get("errmsg");
            log.error("错误码:"+resultInt);
            log.error("错误信息:"+errmsg);
            throw new GlobalException("验证码发送失败",-1,null);
        }
        redisTemplate.opsForValue().set(phone,validateCode.getCode(), Long.parseLong(String.valueOf(validateCode.getSmsExpire())), TimeUnit.SECONDS);

    }
}
