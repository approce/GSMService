package com.service;

import com.DAO.AggregatorDAO;

/**
 * Created by Roman Zayats on 26.03.2015.
 */
public interface AggregatorService {
    void initialization();

    void setAggregatorDAO(AggregatorDAO aggregatorDAO);
}
