package com.example.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试接口")
@RestController("/demo")
public class HelloController {

    @GetMapping("/hello")
    public String SayHello() {
        return "Hello World!";
    }

    @GetMapping("/index")
    public Object index(Authentication authentication) {
        return authentication;
    }
}
