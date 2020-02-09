package com.mall;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : Vander
 * @date :   2019/8/3
 * @description :
 */
@EnableDubbo(scanBasePackages = "com.mall")
@SpringBootApplication
public class ShoppingMallSellerGoodsServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(ShoppingMallSellerGoodsServiceApplication.class, args);
    }
}
