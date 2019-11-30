package com.newperson.springbootsecurity.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 11:20
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
