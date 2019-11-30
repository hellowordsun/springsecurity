package com.newperson.springbootsecurity.core.properties;

import lombok.Data;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/30 17:04
 */
@Data
public class QQProperties  {

    private String appId;

    private String appSecret;
    /**
     * 服务供应商 qq或者weixin 等等
     */
    private String providerId="qq";
}
