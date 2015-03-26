package com.service;

import com.DAO.AggregatorDAO;
import com.model.Aggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Roman Zayats on 26.03.2015.
 */
@Service(value = "AggregatorService")
public class AggregatorServiceImpl implements AggregatorService {

    @Autowired
    private AggregatorDAO aggregatorDAO;

    private static List<Aggregator> AGGREGATOR_LIST;

    @PostConstruct
    public void initialization() {
        AGGREGATOR_LIST = aggregatorDAO.getAggregators();
    }
}
