package com.hello.cigarettes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hello.cigarettes.dao")
@SpringBootApplication
public class CigarettesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CigarettesApplication.class, args);
    }
}
