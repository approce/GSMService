package sms.com.service;

import sms.com.aggregators.AggregatorExecutor;

import java.util.List;

public interface AggregatorPoolService {

    List<AggregatorExecutor> getAggregators();

    AggregatorExecutor getAggregatorByGateway(String id);
}
