package com.newperson.springbootsecurity.core.properties;

import lombok.Data;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 14:56
 */
@Data
public class smsCodeProperties {

    /**
     * 验证码长度
     */
    private int length;
    /**
     * 过期时间 秒为单位
     */
    private int expire;
    /**
     * 需要进行图片验证的路径
     */
    private String smsUrl;

    /**
     * appId
     */
    private Integer appID;
    /**
     * appKey
     */
    private String appKey;
    /**
     * 模板id
     */
    private Integer templateId;

    /**
     * 签名
     */
    private String signName;
}
