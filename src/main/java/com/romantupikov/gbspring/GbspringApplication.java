package com.romantupikov.gbspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GbspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(GbspringApplication.class, args);
    }
}
