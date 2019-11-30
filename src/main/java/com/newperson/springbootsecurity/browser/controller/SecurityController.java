package com.newperson.springbootsecurity.browser.controller;

import com.newperson.springbootsecurity.core.properties.SecurityProperties;
import com.newperson.springbootsecurity.demo.utils.JsonData;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/27 12:01
 *
 */
@RestController
@Slf4j
public class SecurityController {

    /**
     *  首先获取请求缓存
     */
    private RequestCache requestCache=new HttpSessionRequestCache();

    /**
     * springsecurity提供的跳转对象
     */
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @ApiOperation("如果未登录的情况下，是跳到html还是返回json")
    @RequestMapping("/authorization/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED) //未授权的状态码，
    public JsonData requireAuthorization(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest!=null) {
            //获取用户请求的url路径
            String url = savedRequest.getRedirectUrl();
            log.info("用户请求的url是:"+url);
            //如果是html
            if (StringUtils.endsWith(url, ".html")) {
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        return JsonData.buildError("用户未授权，请去登陆",401);
    }
}
