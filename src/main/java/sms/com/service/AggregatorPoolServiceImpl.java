package sms.com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.aggregators.AbstractAggregatorFacade;

import java.util.List;

@Service
public class AggregatorPoolServiceImpl implements AggregatorPoolService {

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorPoolServiceImpl.class);

    @Autowired
    private List<AbstractAggregatorFacade> abstractAggregatorFacadeList;

    @Override
    public List<AbstractAggregatorFacade> getAggregators() {
        return abstractAggregatorFacadeList;
    }

    @Override
    public AbstractAggregatorFacade getAggregatorByGateway(String id) {
        return abstractAggregatorFacadeList.stream()
                                     .filter(ag -> ag.getId().equals(id))
                                     .findFirst().get();
    }
}
