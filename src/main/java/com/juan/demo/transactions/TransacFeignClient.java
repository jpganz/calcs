package com.juan.demo.transactions;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(
         name="transac-rest-client",
         url="http://localhost:8080"
    )

public interface TransacFeignClient {

    @RequestMapping(method = GET, value = "/transac", produces = APPLICATION_JSON_VALUE)
    List<Double> getTransactions();
}
