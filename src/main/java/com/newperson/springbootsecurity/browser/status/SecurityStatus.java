package com.newperson.springbootsecurity.browser.status;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/30 11:24
 */
public class SecurityStatus {

    /**
     * 账号密码登录的url
     */
    public static final String  LOGIN_URL="/authorization/login";
    /**
     * 未授权时需要跳转的url
     */
    public static final  String NO_PERMISSION_URL="/authorization/require";
    /**
     * 使用账号接受的参数名
     */
    public static final  String USERNAME="username";

    /**
     * 使用密码接受的参数名
     */
    public static final  String PASSWORD="password";

    /**
     * 短信验证码跟图片验证码不拦截的url
     */
    public static final  String SMS_IMAGE_URL="/code/*";


}
