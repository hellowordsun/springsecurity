package com.newperson.springbootsecurity.core.social.qq.config;

import com.newperson.springbootsecurity.core.properties.QQProperties;
import com.newperson.springbootsecurity.core.properties.SecurityProperties;
import com.newperson.springbootsecurity.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/30 17:13
 */
@Component
@ConditionalOnProperty(prefix = "newperson.security.social.qq",name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(),qq.getAppId(),qq.getAppSecret());
    }
}
