package com.juan.demo.config;

import com.juan.demo.service.StatisticCalculatorService;
import com.juan.demo.service.StatisticCalculatorServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    StatisticCalculatorService statisticCalculator(){
        return new StatisticCalculatorServiceImpl();
    }
}
