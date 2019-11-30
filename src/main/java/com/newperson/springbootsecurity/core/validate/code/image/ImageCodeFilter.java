package com.newperson.springbootsecurity.core.validate.code.image;

import com.newperson.springbootsecurity.browser.authentication.FailAuthentication;
import com.newperson.springbootsecurity.core.properties.SecurityProperties;
import com.newperson.springbootsecurity.core.validate.code.ValidateCodeController;
import com.newperson.springbootsecurity.core.validate.code.ValidateCodeException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 11:28
 *
 * 这个过滤器只会执行一次
 */

@Slf4j
public class ImageCodeFilter extends OncePerRequestFilter implements InitializingBean {
    @Setter
    private FailAuthentication failAuthentication;
    @Setter
    private SecurityProperties securityProperties;

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    private Set<String> set=new HashSet<>();

    private AntPathMatcher antPathMatcher=new AntPathMatcher();


    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String imageUrls = securityProperties.getCode().getImage().getImageUrl();
        String[] strings = StringUtils.splitByWholeSeparatorPreserveAllTokens(imageUrls, ",");
        Arrays.stream(strings).forEach(imageUrl->set.add(imageUrl));

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean flag=false;
        for (String url:set){
            if (antPathMatcher.match(url,request.getRequestURI())) {
                flag=true;
                break;
            }
        }

        if (flag) {
            try {
                validate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){
                failAuthentication.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        //如果验证码正确，或者不是等登录路径，直接放行
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        //从session中获取到验证码
        ImageCode imageCode=(ImageCode)sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
        //获取到前台传过来的验证码
        String requestCode= request.getParameter("imageCode");


        if (StringUtils.isBlank(requestCode)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (imageCode==null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (imageCode.isExpire()) {
            sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(imageCode.getCode(),requestCode)) {
            throw new ValidateCodeException("验证码错误");
        }
        //正确的话，删除原来的
        sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);

    }
}
