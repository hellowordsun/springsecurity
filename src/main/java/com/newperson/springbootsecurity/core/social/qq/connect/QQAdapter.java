package com.newperson.springbootsecurity.core.social.qq.connect;

import com.newperson.springbootsecurity.core.social.qq.api.QQ;
import com.newperson.springbootsecurity.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/30 16:05
 */
public class QQAdapter implements ApiAdapter<QQ> {
    /**
     * 不需要测试，直接用,默认直接是true
     * @param qq
     * @return
     */
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        QQUserInfo userInfo = qq.getUserInfo();
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
        connectionValues.setProfileUrl(null);
        connectionValues.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    /**
     * 某些社交网站才用得到，例如微博等，qq没有
     * @param qq
     * @param s
     */
    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
