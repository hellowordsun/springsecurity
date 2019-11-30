package com.newperson.springbootsecurity.core.validate.code;

import com.newperson.springbootsecurity.core.validate.code.image.ImageCodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 15:54
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator=new ImageCodeGenerator();
        return imageCodeGenerator;
    }

}
