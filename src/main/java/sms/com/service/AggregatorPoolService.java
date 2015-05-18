package sms.com.service;

import sms.com.aggregators.AggregatorFacade;

import java.util.List;

public interface AggregatorPoolService {

    List<AggregatorFacade> getAggregators();

    AggregatorFacade getAggregatorByGateway(String id);
}
