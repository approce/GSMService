package com.service.interfaces;

import com.DAO.AggregatorDAO;
import com.model.aggregator.Aggregator;

import java.util.List;

public interface AggregatorService {
    void initialize();

    List<Aggregator> getAggregatorList();

    void startAggregator(Aggregator aggregator);

    void setAggregatorDAO(AggregatorDAO aggregatorDAO);
}
