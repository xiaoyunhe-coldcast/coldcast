package com.coldcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication //Spring Boot核心注解，用于开启自动配置
@ComponentScan(basePackages = "com.coldcast.*")
//@EnableJms //可加可不加，建议加上
public class DemoApplication {
  
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
}