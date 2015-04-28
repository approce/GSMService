package com.model.aggregator;

import com.model.Modem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticalAggregatorExecutorImpl extends AggregatorExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(VerticalAggregatorExecutorImpl.class);

    public VerticalAggregatorExecutorImpl(String ID, Boolean START_ON_SETUP, Modem MODEM) {
        super(ID, START_ON_SETUP, MODEM);
    }

}
