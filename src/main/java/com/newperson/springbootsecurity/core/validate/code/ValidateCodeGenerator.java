package com.newperson.springbootsecurity.core.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 15:47
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(HttpServletRequest request);
}
