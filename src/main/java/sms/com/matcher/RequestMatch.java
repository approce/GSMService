package sms.com.matcher;

import sms.com.aggregators.AggregatorExecutor;

public class RequestMatch {

    private AggregatorExecutor aggregatorExecutor;

    private boolean canBeExecuted;

    private Double match_index;

    public AggregatorExecutor getAggregatorExecutor() {
        return aggregatorExecutor;
    }

    public void setAggregatorExecutor(AggregatorExecutor aggregatorExecutor) {
        this.aggregatorExecutor = aggregatorExecutor;
    }

    public boolean isExecutable() {
        return canBeExecuted;
    }

    public void setCanBeExecuted(boolean canBeExecuted) {
        this.canBeExecuted = canBeExecuted;
    }

    public Double getMatch_index() {
        return match_index;
    }

    public void setMatch_index(Double match_index) {
        this.match_index = match_index;
    }
}