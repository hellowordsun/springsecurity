package com.newperson.springbootsecurity.core.authentication.mobile;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/29 15:52
 */
@Data
public class SmsCodeAuthenticationProvider  implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsCodeAuthenticationToken token=(SmsCodeAuthenticationToken)authentication;
        UserDetails userDetails=userDetailsService.loadUserByUsername((String) token.getPrincipal());
        if (StringUtils.isBlank(userDetails.getUsername())) {
            throw new InternalAuthenticationServiceException("手机号不存在");
        }
        //代表认证成功，将新的用户信息跟权限重新设置
        SmsCodeAuthenticationToken authenticationToken=new SmsCodeAuthenticationToken(userDetails,userDetails.getAuthorities());
        authenticationToken.setDetails(token.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
