package com.newperson.springbootsecurity.demo.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/26 15:20
 */
@RestController
public class OrderController {

    @GetMapping("/order")
    public String add() throws InterruptedException {
        Thread.sleep(1000L);
        return "success";
    }

    @GetMapping("/order/ascy")
    public Callable<String> addAscy() {
        Callable<String> callable=()->{
            System.out.println("子线程请求开始");
            Thread.sleep(1000);
            System.out.println("子线程请求结束");
            return "success";
        };

        return callable;
    }
}
