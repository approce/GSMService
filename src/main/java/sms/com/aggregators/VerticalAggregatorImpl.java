package sms.com.aggregators;

public class VerticalAggregatorImpl extends AbstractAggregatorFacade {

    private int AVAILABLE_COUNT;
    //TODO: Arduino logic

    public VerticalAggregatorImpl(String id, Boolean startOnSetup, int availableCount) {
        super(id, startOnSetup);
        this.AVAILABLE_COUNT = availableCount;
    }

}
