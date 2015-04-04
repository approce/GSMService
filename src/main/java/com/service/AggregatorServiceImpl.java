package com.service;

import com.model.aggregator.AggregatorExecutor;
import com.service.interfaces.AggregatorService;
import com.service.interfaces.SMSLibService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AggregatorServiceImpl implements AggregatorService {

    @Autowired
    private SMSLibService smsLibService;
    @Autowired
    private List<AggregatorExecutor> aggregatorExecutors;

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorServiceImpl.class);
    private static List<AggregatorExecutor> AGGREGATOR_LIST;

    @Override
    public List<AggregatorExecutor> getAggregatorList() {
        return AGGREGATOR_LIST;
    }

    @Override
    public AggregatorExecutor getAggregatorExecutorByGateway(AGateway gateway) throws Exception {
        for (AggregatorExecutor aggregatorExecutor : AGGREGATOR_LIST) {
            if (aggregatorExecutor.getModem().equals(gateway)) {
                return aggregatorExecutor;
            }
        }
        throw new Exception();
    }

}
