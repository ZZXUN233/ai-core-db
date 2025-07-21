package com.zzxun.aicorebd.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.zzxun"})
public class AiCoreBdApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCoreBdApplication.class, args);
    }
}
