package com.juan.demo.service;

import com.juan.demo.model.Calcs;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StatisticCalculatorServiceTest {

    private static final List<Double> TRANSACTION_VALUES = new ArrayList<>();

    private StatisticCalculatorService statisticCalculatorService;

    @Before
    public void setUp() {
        statisticCalculatorService = new StatisticCalculatorServiceImpl();
        TRANSACTION_VALUES.add(10.50);
        TRANSACTION_VALUES.add(24.50);
        TRANSACTION_VALUES.add(65.00);
        TRANSACTION_VALUES.add(20.00);
    }

    @Test
    public void testSaveANewTransactionWithPastDate() {
        final Calcs calcsMadeByHand = new Calcs();
        calcsMadeByHand.setAverage(30.00);
        calcsMadeByHand.setCount(4);
        calcsMadeByHand.setMax(65.00);
        calcsMadeByHand.setMin(10.50);
        calcsMadeByHand.setSum(120.00);
        final Calcs returnedCalcs = statisticCalculatorService.getTransacsValues(TRANSACTION_VALUES);
        assertThat(returnedCalcs.getAverage(), is(calcsMadeByHand.getAverage()));
        assertThat(returnedCalcs.getCount(), is(calcsMadeByHand.getCount()));
        assertThat(returnedCalcs.getMax(), is(calcsMadeByHand.getMax()));
        assertThat(returnedCalcs.getMin(), is(calcsMadeByHand.getMin()));
        assertThat(returnedCalcs.getSum(), is(calcsMadeByHand.getSum()));
    }
}
