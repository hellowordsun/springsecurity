package com.newperson.springbootsecurity.demo.user.controller;

import com.newperson.springbootsecurity.demo.user.dto.User;
import com.newperson.springbootsecurity.demo.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/25 17:02
 */
@RestController
@Slf4j
@RequestMapping("/user")
@Api("用户的接口")
public class UserController {


    /**
     * 获取用户信息
     *
     * @param
     * @return
     */
    @GetMapping("/info")
    public Object getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;

    }


    @GetMapping
    @ApiOperation("用户查询接口")
    public List<User> getUsers(User user) {
       // throw  new RuntimeException("666");
        System.out.println(user);
        List<User> users= Arrays.asList(new User("张三","123"),new User("李四","456"));
        return users;
    }

    @GetMapping("/{id:\\d+}")
    public Object getUserInfo(@PathVariable Integer id) {
        System.out.println(id);
        return new User("java","999");

    }

    @PostMapping
    public Object createUser(@Valid @RequestBody User user) {
        System.out.println(user);
        user.setId(1L);
        return user;
    }

    @PutMapping
    public Object updateUser(@Valid @RequestBody User user) {

        System.out.println(user);
        return JsonData.buildSuccess(user);
    }

    @DeleteMapping("{id:\\d+}")
    public void delete(@PathVariable  Long id) {
        System.out.println(id);
    }
}
