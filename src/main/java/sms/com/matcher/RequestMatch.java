package sms.com.matcher;

import sms.com.aggregators.AbstractAggregatorFacade;

public class RequestMatch {

    private AbstractAggregatorFacade abstractAggregatorFacade;

    private boolean canBeExecuted;

    private Double match_index;

    public AbstractAggregatorFacade getAbstractAggregatorFacade() {
        return abstractAggregatorFacade;
    }

    public void setAbstractAggregatorFacade(AbstractAggregatorFacade abstractAggregatorFacade) {
        this.abstractAggregatorFacade = abstractAggregatorFacade;
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