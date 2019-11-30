package com.newperson.springbootsecurity.demo.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/26 14:58
 */
@Component
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "sftp.client",ignoreUnknownFields =true)
public class SftpProperties {

    private String protocol;

    private String host;

    private String ip;

    private String username;

    private String password;

    private String path;

    private String sessionConnectTimeout;

    private String  channelConnectedTimeout;

}
