package com.newperson.springbootsecurity.demo.code;

import com.newperson.springbootsecurity.core.validate.code.ValidateCodeGenerator;
import com.newperson.springbootsecurity.core.validate.code.image.ImageCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 16:00
 */
//@Component("imageCodeGenerator")
public class CustomerValidateImageCode implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(HttpServletRequest request) {

        System.out.println("覆盖掉了");
        return null;
    }
}
