package com.newperson.springbootsecurity.core.social.qq.connect;

import com.newperson.springbootsecurity.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/30 16:09
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
