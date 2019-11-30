package com.newperson.springbootsecurity.core.properties;

import lombok.Data;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 14:56
 */
@Data
public class ImageCodeProperties {
    /**
     * 验证码宽度
     */
    private int width=160;
    /**
     * 验证码长度
     */
    private int height=30;
    /**
     * 验证码长度
     */
    private int length=5;
    /**
     * 干扰线的多少
     */
    private int lines=150;
    /**
     * 过期时间 秒为单位
     */
    private int expire=60;
    /**
     * 需要进行图片验证的路径
     */
    private String imageUrl;
}
