package com.juan.demo.service;

import com.juan.demo.model.Calcs;

import java.util.List;

public interface StatisticCalculatorService {

    Calcs getTransacsValues(final List<Double> collectedInfo);
}
