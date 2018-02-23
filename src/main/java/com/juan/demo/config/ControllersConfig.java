package com.juan.demo.config;

import com.juan.demo.controller.StatsController;
import com.juan.demo.service.StatisticCalculatorService;
import com.juan.demo.transactions.TransacFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersConfig {

    @Bean
    public StatsController statsController(final TransacFeignClient transacFeignClient, final StatisticCalculatorService statisticCalculatorService) {
        return new StatsController(transacFeignClient, statisticCalculatorService);
    }
}
