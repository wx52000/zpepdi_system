package com.zpepdi.qj_heating;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan("com.zpepdi.qj_heating.dao")
@MapperScan("com.zpepdi.qj_heating.dao")
public class QjHeatingApplication {

    public static void main(String[] args) {
        SpringApplication.run(QjHeatingApplication.class, args);
    }


}

