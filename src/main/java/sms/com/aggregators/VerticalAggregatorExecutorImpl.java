package sms.com.aggregators;

import sms.com.modem.ModemExecutor;

public class VerticalAggregatorExecutorImpl extends AggregatorExecutor {

    private int AVAILABLE_COUNT;

    public VerticalAggregatorExecutorImpl(String id,
                                          Boolean startOnSetup,
                                          ModemExecutor modemExecutor,
                                          SIMExecutor simExecutor,
                                          AggregatorRequestMatcher aggregatorRequestMatcher,
                                          int availableCount) {
        super(id, startOnSetup, modemExecutor, simExecutor, aggregatorRequestMatcher);
        this.AVAILABLE_COUNT = availableCount;
    }

}
