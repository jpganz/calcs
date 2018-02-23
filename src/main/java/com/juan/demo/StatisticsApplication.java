package com.juan.demo;

import com.juan.demo.config.ControllersConfig;
import com.juan.demo.config.ServiceConfig;
import com.juan.demo.config.SwaggerConfig;
import com.juan.demo.transactions.TransacFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
@Configuration
@EnableAutoConfiguration //todo: change this tag and manually import required configurators.
@EnableFeignClients
@Import( {
                 ServiceConfig.class,
                 ControllersConfig.class,
                 SwaggerConfig.class,
                 TransacFeignClient.class

         })
public class StatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);

    }
}
