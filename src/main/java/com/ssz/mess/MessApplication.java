package com.ssz.mess;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ssz.mess.mapper")
public class MessApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessApplication.class, args);
    }

}
