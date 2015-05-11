package sms.com.aggregators;

import sms.com.matcher.RequestMatch;
import sms.com.model.Request;
import sms.com.modem.ModemExecutor;

public class VerticalAggregatorExecutorImpl extends AggregatorExecutor {

    private int AVAILABLE_COUNT;

    public VerticalAggregatorExecutorImpl(String id,
                                          Boolean startOnSetup,
                                          ModemExecutor modemExecutor,
                                          SIMExecutor simExecutor,
                                          int availableCount) {
        super(id, startOnSetup, modemExecutor,simExecutor);
        this.AVAILABLE_COUNT = availableCount;
    }

    @Override
    public RequestMatch match(Request request) {
        //TODO add logic.
        return new RequestMatch();
    }
}
