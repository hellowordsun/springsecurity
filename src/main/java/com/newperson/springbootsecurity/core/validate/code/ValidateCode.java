package com.newperson.springbootsecurity.core.validate.code;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 10:41
 */
@Data
public class ValidateCode {

    private String code;
    private LocalDateTime expireTime;
    private int smsExpire;


    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code=code;
        this.expireTime=expireTime;
    }

    public ValidateCode(String code, int  expire) {
        this.code=code;
        this.expireTime=LocalDateTime.now().plusSeconds(expire);
        this.smsExpire=expire;
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
