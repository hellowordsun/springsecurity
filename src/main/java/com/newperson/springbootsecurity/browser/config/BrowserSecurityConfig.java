package com.newperson.springbootsecurity.browser.config;

import com.newperson.springbootsecurity.browser.authentication.FailAuthentication;
import com.newperson.springbootsecurity.browser.authentication.SuccessAuthentication;
import com.newperson.springbootsecurity.browser.status.SecurityStatus;
import com.newperson.springbootsecurity.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.newperson.springbootsecurity.core.properties.SecurityProperties;
import com.newperson.springbootsecurity.core.validate.code.image.ImageCodeFilter;
import com.newperson.springbootsecurity.core.validate.code.sms.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/26 17:31
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SuccessAuthentication successAuthentication;

    @Autowired
    private FailAuthentication failAuthentication;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private SmsCodeAuthenticationSecurityConfig securityConfig;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //因为在过滤器中 有两个set集合，所以不能设置成spring的单例 所以必须要new出来
        ImageCodeFilter imageCodeFilter=new ImageCodeFilter();
        imageCodeFilter.setSecurityProperties(securityProperties);
        imageCodeFilter.setFailAuthentication(failAuthentication);
        imageCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter=new SmsCodeFilter();
        smsCodeFilter.setFailAuthentication(failAuthentication);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.setRedisTemplate(redisTemplate);
        smsCodeFilter.afterPropertiesSet();
                 http
                         .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                        .addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class)
                        .formLogin()
                        .loginPage(SecurityStatus.NO_PERMISSION_URL)
                        .loginProcessingUrl(SecurityStatus.LOGIN_URL)
                        .successHandler(successAuthentication).failureHandler(failAuthentication)
                        .usernameParameter(SecurityStatus.USERNAME)
                        .passwordParameter(SecurityStatus.PASSWORD)
                .and().
                         apply(springSocialConfigurer)
                 .and()
                         .rememberMe()
                         .tokenRepository(tokenRepository())
                         .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeExpire())
                         .userDetailsService(userDetailsService)
                 .and()
                        .authorizeRequests()
                        .antMatchers(SecurityStatus.NO_PERMISSION_URL,securityProperties.getBrowser().getLoginPage(),SecurityStatus.SMS_IMAGE_URL)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                .and()
                        .csrf().disable()
                         .apply(securityConfig);
                // http.addFilter(userAuthenticationFilterBean());
    }

    @Bean
    public UserAuthenticationFilter userAuthenticationFilterBean() throws Exception {
        UserAuthenticationFilter userAuthenticationFilter = new UserAuthenticationFilter();
        userAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        userAuthenticationFilter.setAuthenticationSuccessHandler(successAuthentication);
        userAuthenticationFilter.setAuthenticationFailureHandler(failAuthentication);
        return userAuthenticationFilter;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl repository=new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        //自动建表,如果已经存在了，则不需要了
       // repository.setCreateTableOnStartup(true);
        return repository;

    }

}
