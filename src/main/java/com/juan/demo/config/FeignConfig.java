package com.juan.demo.config;

import com.juan.demo.transactions.TransacFeignClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableHystrix
@EnableFeignClients(clients = {TransacFeignClient.class})
@Import(FeignAutoConfiguration.class)
public class FeignConfig {
}
