package com.newperson.springbootsecurity.core.properties;

import com.newperson.springbootsecurity.core.enums.LoginType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/27 14:27
 */
@Data
@NoArgsConstructor
public class SecurityBrowserProperties {

    private String loginPage;

    private LoginType loginType=LoginType.JSON;

    /**
     * 记住我的过期时间默认是1小时
     */
    private  int rememberMeExpire=3600;
}
