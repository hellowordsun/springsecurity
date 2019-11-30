package com.newperson.springbootsecurity.browser.authentication;

import com.google.gson.Gson;
import com.newperson.springbootsecurity.core.enums.LoginType;
import com.newperson.springbootsecurity.core.properties.SecurityProperties;
import com.newperson.springbootsecurity.demo.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/27 15:52
 */
@Component("failAuthentication")
@Slf4j
public class FailAuthentication extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private Gson gson;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败");

        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(gson.toJson(JsonData.buildError(exception.getMessage())));
        }else{
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}
