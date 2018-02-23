package com.juan.demo.config;

import com.juan.demo.controller.StatsController;
import com.juan.demo.service.StatisticCalculator;
import com.juan.demo.transactions.TransacFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersConfig {

    @Bean
    public StatsController statsController(final TransacFeignClient transacFeignClient, final StatisticCalculator statisticCalculator) {
        return new StatsController(transacFeignClient, statisticCalculator);
    }
}
