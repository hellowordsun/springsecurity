package com.newperson.springbootsecurity.browser.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newperson.springbootsecurity.browser.dao.UserDao;
import com.newperson.springbootsecurity.browser.entity.User;
import com.newperson.springbootsecurity.browser.service.UserService;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {



}
