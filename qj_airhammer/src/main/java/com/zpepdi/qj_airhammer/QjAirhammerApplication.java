package com.zpepdi.qj_airhammer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaServer
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.zpepdi.qj_airhammer"})
@MapperScan(basePackages = "com.zpepdi.qj_airhammer.dao")
public class QjAirhammerApplication {
    public static void main(String[] args) {
        SpringApplication.run(QjAirhammerApplication.class, args);
    }

}
