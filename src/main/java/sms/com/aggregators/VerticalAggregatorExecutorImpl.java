package sms.com.aggregators;

import sms.com.modem.ModemExecutor;

public class VerticalAggregatorExecutorImpl extends AggregatorExecutor {

    private int AVAILABLE_COUNT;

    public VerticalAggregatorExecutorImpl(String id,
                                          Boolean startOnSetup,
                                          ModemExecutor modemExecutor,
                                          SIMExecutor simExecutor,
                                          AggregatorRequestExecutor aggregatorRequestExecutor,
                                          int availableCount) {
        super(id, startOnSetup, modemExecutor, simExecutor, aggregatorRequestExecutor);
        this.AVAILABLE_COUNT = availableCount;
    }

}
