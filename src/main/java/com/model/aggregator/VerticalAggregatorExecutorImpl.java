package com.model.aggregator;

import com.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticalAggregatorExecutorImpl extends AggregatorExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(VerticalAggregatorExecutorImpl.class);

    @Override
    public void setUSSDMessage(String message) {

    }

    @Override
    public void setMessage(Message message) {

    }

    @Override
    public void setAggregator(Aggregator aggregator) {
        if (aggregator instanceof VerticalAggregator) {
            this.verticalAggregator = (VerticalAggregator) aggregator;
        } else {
            LOG.error("Wrong aggregator set to {}", this.getClass().getName());
        }
    }

    @Override
    public Aggregator getAggregator() {
        return verticalAggregator;
    }
}
