package com.juan.demo.controller;

import com.juan.demo.model.Calcs;
import com.juan.demo.service.StatisticCalculatorService;
import com.juan.demo.transactions.TransacFeignClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatsControllerTest {

    private static final List<Double> TRANSACTION_VALUES = new ArrayList<Double>() {{
        add(10.50);
        add(24.50);
        add(65.00);
        add(20.00);
    }};

    @Mock
    private StatisticCalculatorService statisticCalculatorService;

    @Mock
    private TransacFeignClient transacFeignClient;

    private StatsController statsController;

    @Before
    public void setUp() {
        statsController = new StatsController(transacFeignClient, statisticCalculatorService);
    }

    @Test
    public void testGetStatistics() {
        final Calcs calcsMade = new Calcs();
        calcsMade.setAverage(30.00);
        calcsMade.setCount(4);
        calcsMade.setMax(65.00);
        calcsMade.setMin(10.50);
        calcsMade.setSum(120.00);
        when(transacFeignClient.getTransactions()).thenReturn(TRANSACTION_VALUES);
        when(statisticCalculatorService.getTransacsValues(TRANSACTION_VALUES)).thenReturn(calcsMade);
        final ResponseEntity responseEntity = statsController.getStatistics();
        verify(statisticCalculatorService, times(1)).getTransacsValues(any(List.class));
        verify(transacFeignClient, times(1)).getTransactions();
        assertThat(responseEntity.getStatusCode().value(), is(200));
        final Calcs returnedCalcs = (Calcs) responseEntity.getBody();
        assertThat(returnedCalcs.getAverage(), is(calcsMade.getAverage()));
        assertThat(returnedCalcs.getSum(), is(TRANSACTION_VALUES.stream().mapToDouble(f -> f.doubleValue()).sum()));
    }

    @Test
    public void testGetStatisticsWhenWeGetException() {
        when(transacFeignClient.getTransactions()).thenThrow(new RuntimeException());
        final ResponseEntity responseEntity = statsController.getStatistics();
        verify(transacFeignClient, times(1)).getTransactions();
        verify(statisticCalculatorService, never()).getTransacsValues(any(List.class));
        assertThat(responseEntity.getStatusCode().value(), is(500));
    }
}
