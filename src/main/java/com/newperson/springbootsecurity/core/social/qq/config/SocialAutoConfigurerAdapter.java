package com.newperson.springbootsecurity.core.social.qq.config;

import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/30 17:16
 */
public abstract class SocialAutoConfigurerAdapter extends SocialConfigurerAdapter {
    public SocialAutoConfigurerAdapter() {
    }
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(this.createConnectionFactory());
    }
    protected abstract ConnectionFactory<?> createConnectionFactory();
}
