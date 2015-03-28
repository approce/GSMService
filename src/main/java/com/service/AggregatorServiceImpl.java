package com.service;

import com.DAO.AggregatorDAO;
import com.model.aggregator.Aggregator;
import com.service.interfaces.AggregatorService;
import com.service.interfaces.SMSLibService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service(value = "AggregatorService")
public class AggregatorServiceImpl implements AggregatorService {

    @Autowired
    private AggregatorDAO aggregatorDAO;
    @Autowired
    private SMSLibService smsLibService;

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorServiceImpl.class);
    private static List<Aggregator> AGGREGATOR_LIST;

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Start aggregators initialization");
        AGGREGATOR_LIST = aggregatorDAO.getAggregators();
        LOG.debug("Aggregator available count: {}", AGGREGATOR_LIST.size());

        for (Aggregator aggregator : AGGREGATOR_LIST) {
            //smsLibService.addGateway(aggregator.getModem());
        }
    }

    @Override
    public List<Aggregator> getAggregatorList() {
        return AGGREGATOR_LIST;
    }


    @Override
    public void setAggregatorDAO(AggregatorDAO aggregatorDAO) {
        this.aggregatorDAO = aggregatorDAO;
    }
}
