package com.service;

import com.DAO.interfaces.AggregatorExecutorDAO;
import com.model.aggregator.AggregatorExecutor;
import com.service.interfaces.AggregatorService;
import com.service.interfaces.SMSLibService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service(value = "AggregatorService")
public class AggregatorServiceImpl implements AggregatorService {

    @Autowired
    private AggregatorExecutorDAO aggregatorExecutorDAO;
    @Autowired
    private SMSLibService smsLibService;

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorServiceImpl.class);
    private static List<AggregatorExecutor> AGGREGATOR_LIST;

    @PostConstruct
    public void initialize() {
        LOG.debug("Start aggregators initialization");
        AGGREGATOR_LIST = aggregatorExecutorDAO.getAggregatorExecutors();
        LOG.debug("Aggregator available count: {}", AGGREGATOR_LIST.size());

        for (AggregatorExecutor aggregatorExecutor : AGGREGATOR_LIST) {
            if (aggregatorExecutor.isStartOnSetup()) {
                smsLibService.addGateway(aggregatorExecutor.getAggregator().getModem());
            }
        }
        smsLibService.startService();
    }

    @Override
    public List<AggregatorExecutor> getAggregatorList() {
        return AGGREGATOR_LIST;
    }

    @Override
    public AggregatorExecutor getAggregatorExecutorByGateway(AGateway gateway) throws Exception {
        for (AggregatorExecutor aggregatorExecutor : AGGREGATOR_LIST) {
            if (aggregatorExecutor.getAggregator().getModem().equals(gateway)) {
                return aggregatorExecutor;
            }
        }
        throw new Exception();
    }

}
