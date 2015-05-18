package sms.com.matcher;

import sms.com.aggregators.AggregatorFacade;

public class RequestMatch {

    private AggregatorFacade aggregatorFacade;

    private boolean canBeExecuted;

    private Double match_index;

    public AggregatorFacade getAggregatorFacade() {
        return aggregatorFacade;
    }

    public void setAggregatorFacade(AggregatorFacade aggregatorFacade) {
        this.aggregatorFacade = aggregatorFacade;
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