package sms.com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.aggregators.AggregatorFacade;

import java.util.List;

@Service
public class AggregatorPoolServiceImpl implements AggregatorPoolService {

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorPoolServiceImpl.class);

    @Autowired
    private List<AggregatorFacade> aggregatorFacadeList;

    @Override
    public List<AggregatorFacade> getAggregators() {
        return aggregatorFacadeList;
    }

    @Override
    public AggregatorFacade getAggregatorByGateway(String id) {
        return aggregatorFacadeList.stream()
                                     .filter(ag -> ag.getGatewayId().equals(id))
                                     .findFirst().get();
    }
}
