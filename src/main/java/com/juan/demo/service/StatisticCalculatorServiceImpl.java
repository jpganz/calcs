package com.juan.demo.service;

import com.juan.demo.model.Calcs;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticCalculatorServiceImpl implements StatisticCalculatorService {

    //todo: check why one stream results more expensive than this way...
    //todo: format to have 2 decimals only?
    @Override
    public Calcs getTransacsValues(List<Double> collectedInfo) {
        final Calcs calcs = new Calcs();
        final List<Double> orderedDoubles =
                collectedInfo
                        .stream()
                        .sorted().collect(Collectors.toList());
        final double sum = orderedDoubles.stream().mapToDouble(d -> d).sum();
        final int size = orderedDoubles.size();
        if (size > 0) {
            calcs.setCount(size);
            calcs.setMin(orderedDoubles.get(0));
            calcs.setMax(orderedDoubles.get(size - 1));
            calcs.setSum(sum);
            calcs.setAverage(sum / size); //todo: not sure if format it or not :)
        }
        return calcs;
    }
}
