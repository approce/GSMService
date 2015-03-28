package com.service.interfaces;

import com.DAO.AggregatorDAO;

public interface AggregatorService {
    void initialize();

    void setAggregatorDAO(AggregatorDAO aggregatorDAO);
}
