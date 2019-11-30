package com.newperson.springbootsecurity.core.properties;

import lombok.Data;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 15:02
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private smsCodeProperties sms=new smsCodeProperties();
}
