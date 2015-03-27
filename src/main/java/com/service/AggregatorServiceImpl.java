package com.service;

import com.DAO.AggregatorDAO;
import com.model.aggregator.Aggregator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorServiceImpl.class);

    private static List<Aggregator> AGGREGATOR_LIST;

    @PostConstruct
    public void initialization() {
        LOG.debug("Start aggregators initialization");

        AGGREGATOR_LIST = aggregatorDAO.getAggregators();
        LOG.debug("Aggregator available count: {}", AGGREGATOR_LIST.size());

        for (Aggregator aggregator : AGGREGATOR_LIST) {
            aggregator.initialize();
        }
    }
}
