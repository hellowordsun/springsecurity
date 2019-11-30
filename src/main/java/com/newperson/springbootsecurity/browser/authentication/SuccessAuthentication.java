package com.newperson.springbootsecurity.browser.authentication;

import com.google.gson.Gson;
import com.newperson.springbootsecurity.core.enums.LoginType;
import com.newperson.springbootsecurity.core.properties.SecurityProperties;
import com.newperson.springbootsecurity.demo.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/27 15:07
 */
@Component("successAuthentication")
@Slf4j
public class SuccessAuthentication extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private Gson gson;

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("登录成功");
        //json返回
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(gson.toJson(JsonData.buildSuccess(authentication)));
        }else{
            //页面跳转
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
