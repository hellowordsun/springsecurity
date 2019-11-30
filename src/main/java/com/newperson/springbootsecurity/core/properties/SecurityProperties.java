package com.newperson.springbootsecurity.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/27 14:28
 */
@ConfigurationProperties(prefix = "newperson.security")
//@PropertySource(value = "classpath:application1.properties",encoding = "UTF-8")
@Data
@NoArgsConstructor
public class SecurityProperties {

    private SecurityBrowserProperties browser=new SecurityBrowserProperties();

    private ValidateCodeProperties code=new ValidateCodeProperties();

    private SocialProperties social=new SocialProperties();
}
