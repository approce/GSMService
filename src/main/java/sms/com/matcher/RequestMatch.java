package sms.com.matcher;

import sms.com.aggregators.AggregatorExecutor;

public class RequestMatch {

    private AggregatorExecutor aggregatorExecutor;

    private boolean canBeExecuted;

    public AggregatorExecutor getAggregatorExecutor() {
        return aggregatorExecutor;
    }

    public void setAggregatorExecutor(AggregatorExecutor aggregatorExecutor) {
        this.aggregatorExecutor = aggregatorExecutor;
    }

    public boolean isCanBeExecuted() {
        return canBeExecuted;
    }

    public void setCanBeExecuted(boolean canBeExecuted) {
        this.canBeExecuted = canBeExecuted;
    }
}