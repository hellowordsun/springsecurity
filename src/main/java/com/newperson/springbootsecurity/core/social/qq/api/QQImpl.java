package com.newperson.springbootsecurity.core.social.qq.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/30 14:38
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ  {

    /**
     * 获取openId
     */
    private static final String URL_GET_OPENID="https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取用户信息
     * @return
     */
    private static final  String URL_GET_USER_INFO="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";


    private String appId;
    private String openId;

    public QQImpl(String accessToken,String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId=appId;
        String url=String.format(URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result);
        String openId =(String) jsonObject.get("openid");
        this.openId=openId;
    }


    @Override
    public QQUserInfo getUserInfo() {
        String url=String.format(URL_GET_USER_INFO,appId,openId);
        String userInfo = getRestTemplate().getForObject(url, String.class);
        QQUserInfo qqUserInfo = JSON.parseObject(userInfo, QQUserInfo.class);
        return qqUserInfo;
    }
}
