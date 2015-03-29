package com.service.interfaces;

import com.model.aggregator.AggregatorExecutor;
import org.smslib.AGateway;

import java.util.List;

public interface AggregatorService {

    List<AggregatorExecutor> getAggregatorList();

    public AggregatorExecutor getAggregatorExecutorByGateway(AGateway gateway) throws Exception;
}
