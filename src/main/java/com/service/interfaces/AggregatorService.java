package com.service.interfaces;

import com.DAO.AggregatorDAO;
import com.model.aggregator.Aggregator;

import java.util.List;

public interface AggregatorService {
    void initialize();

    List<Aggregator> getAggregatorList();

    void setAggregatorDAO(AggregatorDAO aggregatorDAO);
}
