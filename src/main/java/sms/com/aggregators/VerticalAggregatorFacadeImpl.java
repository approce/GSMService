package sms.com.aggregators;

import sms.com.modem.ModemExecutor;

public class VerticalAggregatorFacadeImpl extends AggregatorFacade {

    private int AVAILABLE_COUNT;

    public VerticalAggregatorFacadeImpl(String id, Boolean startOnSetup, ModemExecutor modemExecutor,
                                        SIMExecutor simExecutor, AggregatorRequestExecutor aggregatorRequestExecutor,
                                        int availableCount) {
        super(id, startOnSetup, modemExecutor, simExecutor, aggregatorRequestExecutor);
        this.AVAILABLE_COUNT = availableCount;
    }

}
