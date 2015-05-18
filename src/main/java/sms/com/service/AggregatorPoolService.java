package sms.com.service;

import sms.com.aggregators.AbstractAggregatorFacade;

import java.util.List;

public interface AggregatorPoolService {

    List<AbstractAggregatorFacade> getAggregators();

    AbstractAggregatorFacade getAggregatorByGateway(String id);
}
