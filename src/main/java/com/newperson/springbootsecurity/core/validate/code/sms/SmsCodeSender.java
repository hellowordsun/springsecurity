package com.newperson.springbootsecurity.core.validate.code.sms;

import com.newperson.springbootsecurity.core.validate.code.ValidateCode;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 18:13
 */
public interface SmsCodeSender {

    void smsSend(String phone, ValidateCode validateCode) throws Exception;
}
