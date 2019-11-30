package com.newperson.springbootsecurity.core.validate.code.sms;

import com.newperson.springbootsecurity.browser.authentication.FailAuthentication;
import com.newperson.springbootsecurity.core.properties.SecurityProperties;
import com.newperson.springbootsecurity.core.validate.code.ValidateCodeException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
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

public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Setter
    private RedisTemplate redisTemplate;

    @Setter
    private FailAuthentication failAuthentication;

    @Setter
    private SecurityProperties securityProperties;


    private Set<String> set=new HashSet<>();

    private AntPathMatcher antPathMatcher=new AntPathMatcher();


    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String smsUrls = securityProperties.getCode().getSms().getSmsUrl();
        String[] strings = StringUtils.splitByWholeSeparatorPreserveAllTokens(smsUrls, ",");
        Arrays.stream(strings).forEach(smsUrl->set.add(smsUrl));

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
        String mobile=request.getParameter("mobile");
        if (StringUtils.isBlank(mobile)) {
            throw new ValidateCodeException("手机号不能为空");
        }
        //从redis中获取到验证码
        String redisSmsCode = (String)redisTemplate.opsForValue().get(mobile);
        //获取到前台传过来的验证码
        String smsCode= request.getParameter("smsCode");

        if (StringUtils.isBlank(smsCode)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (StringUtils.isBlank(redisSmsCode)) {
            throw new ValidateCodeException("验证码不存在或者过期");
        }

        if (!StringUtils.equalsIgnoreCase(redisSmsCode,smsCode)) {
            throw new ValidateCodeException("验证码错误");
        }
        redisTemplate.delete(mobile);

    }
}
