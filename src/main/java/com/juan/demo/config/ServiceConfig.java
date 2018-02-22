package com.juan.demo.config;


import com.juan.demo.service.StatisticCalculator;
import com.juan.demo.service.StatisticCalculatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    StatisticCalculator statisticCalculator(){
        return new StatisticCalculatorImpl();
    }

}
