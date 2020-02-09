package com.mall;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

/**
 * @author : Vander
 * @date :   2019/8/3
 * @description :
 */
@EnableDubbo(scanBasePackages = "com.mall")
@SpringBootApplication(exclude={JacksonAutoConfiguration.class})
public class ShoppingMallManageWebApplication {
    public static void main(String[] args){
        SpringApplication.run(ShoppingMallManageWebApplication.class);
    }
}
