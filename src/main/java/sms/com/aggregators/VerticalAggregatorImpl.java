package sms.com.aggregators;

public class VerticalAggregatorImpl extends AbstractAggregatorFacade {

    private int AVAILABLE_COUNT;

    public VerticalAggregatorImpl(String id, Boolean startOnSetup, int availableCount) {
        super(id, startOnSetup);
        this.AVAILABLE_COUNT = availableCount;
    }

}
