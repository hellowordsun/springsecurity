package com.newperson.springbootsecurity.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/25 15:44
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
