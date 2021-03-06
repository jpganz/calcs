package com.juan.demo.controller;

import com.codahale.metrics.annotation.Timed;
import com.juan.demo.model.Calcs;
import com.juan.demo.service.StatisticCalculatorService;
import com.juan.demo.transactions.TransacFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Api("Statistics")
@RestController
@RequestMapping(value = "/statistics")
public class StatsController {

    private final TransacFeignClient transacService;
    private final StatisticCalculatorService statisticCalculatorService;

    public StatsController(final TransacFeignClient transacService, final StatisticCalculatorService statisticCalculatorService) {
        this.transacService = transacService;
        this.statisticCalculatorService = statisticCalculatorService;
    }

    @Timed
    @ApiOperation(value = "Get transaction min, max, average, count and sum for the last 60 seconds")
    @ResponseBody
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Calcs> getStatistics() {
        try {
            final List<Double> informationOfLastMinute = transacService.getTransactions();
            return new ResponseEntity<>(statisticCalculatorService.getTransacsValues(informationOfLastMinute), null, OK);
        } catch (Exception e) {
            //todo: use logger
            return new ResponseEntity<>(null, null, INTERNAL_SERVER_ERROR);
        }
    }
}
