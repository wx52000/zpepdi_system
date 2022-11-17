package com.zpepdi.qj_airhammer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.zpepdi.qj_airhammer"})
public class QjAirhammerApplication {
    public static void main(String[] args) {
        SpringApplication.run(QjAirhammerApplication.class, args);
    }
}