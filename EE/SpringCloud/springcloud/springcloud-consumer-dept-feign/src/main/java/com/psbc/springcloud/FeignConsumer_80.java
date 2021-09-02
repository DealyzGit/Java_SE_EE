package com.psbc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.psbc.springcloud"})
public class FeignConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignConsumer_80.class,args);
    }
}
