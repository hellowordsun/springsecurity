package com.newperson.springbootsecurity.browser.config;

import com.newperson.springbootsecurity.browser.dao.UserDao;
import com.newperson.springbootsecurity.browser.entity.Role;
import com.newperson.springbootsecurity.browser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/26 18:49
 */
@Component
public class MyUserDetailConfig implements UserDetailsService , SocialUserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserAndRole(s);
        if (user==null){
            return new org.springframework.security.core.userdetails.User(null,null,null);
        }
        List<Role> roles = user.getRoles();
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        roles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.User(s, user.getPassword(),grantedAuthorities);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        System.out.println(s);
         return new SocialUser(s,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
