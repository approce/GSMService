package com.service.interfaces;

import com.model.aggregator.Aggregator;

import java.util.List;

public interface AggregatorService {
    void initialize();

    List<Aggregator> getAggregatorList();
}
