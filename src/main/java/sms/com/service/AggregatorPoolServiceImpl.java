package sms.com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.aggregators.AggregatorExecutor;

import java.util.List;

@Service
public class AggregatorPoolServiceImpl implements AggregatorPoolService {

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorPoolServiceImpl.class);

    @Autowired
    private List<AggregatorExecutor> aggregatorExecutorList;

    @Override
    public List<AggregatorExecutor> getAggregators() {
        return aggregatorExecutorList;
    }

    @Override
    public AggregatorExecutor getAggregatorByGateway(String id) {
        return aggregatorExecutorList.stream()
                                     .filter(ag -> ag.getGatewayId().equals(id))
                                     .findFirst().get();
    }
}
