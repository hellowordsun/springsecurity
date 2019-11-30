package com.newperson.springbootsecurity.core.properties;

import lombok.Data;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/30 17:08
 */
@Data
public class SocialProperties {
    private QQProperties qq=new QQProperties();
}
