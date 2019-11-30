package com.newperson.springbootsecurity.core.validate.code.sms;

import com.newperson.springbootsecurity.core.properties.SecurityProperties;
import com.newperson.springbootsecurity.core.validate.code.ValidateCode;
import com.newperson.springbootsecurity.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 15:47
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {


    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(HttpServletRequest request) {

        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpire());
    }
}
