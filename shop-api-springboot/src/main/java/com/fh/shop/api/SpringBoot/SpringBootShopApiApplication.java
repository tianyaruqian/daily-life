package com.fh.shop.api.SpringBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hou.SpringBoot.api.brand.hou.*.mapper")
public class SpringBootShopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShopApiApplication.class, args);
    }

}
