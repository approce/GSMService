package sms.com.aggregators;

import sms.com.model.Modem;
import sms.com.model.Request;

public class VerticalAggregatorExecutorImpl extends AggregatorExecutor {

    private int AVAILABLE_COUNT;

    public VerticalAggregatorExecutorImpl(Boolean startOnSetup, Modem modem, String simCell,
                                          int availableCount) {
        super(startOnSetup, modem, simCell);
        this.AVAILABLE_COUNT = availableCount;
    }

    @Override
    public double match(Request request) {
        //TODO add logic.
        return 10;
    }
}
