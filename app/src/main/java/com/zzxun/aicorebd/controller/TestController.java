package com.zzxun.aicorebd.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/testHello")
    public String testHello() {

        return "Hello!";
    }
}
