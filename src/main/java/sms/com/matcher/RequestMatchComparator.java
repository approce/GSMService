package sms.com.matcher;

import java.util.Comparator;

public class RequestMatchComparator implements Comparator<RequestMatch> {

    @Override
    public int compare(RequestMatch o1, RequestMatch o2) {
        // TODO: add implementation
        return 0;
    }
}


    /*private void initMocks(double ag1, double ag2, double ag3) {
        MockitoAnnotations.initMocks(this);

        VerticalAggregatorExecutorImpl aggregator1 =
                mock(VerticalAggregatorExecutorImpl.class);
        when(aggregator1.match(any())).thenReturn(ag1);

        VerticalAggregatorExecutorImpl aggregator2 =
                mock(VerticalAggregatorExecutorImpl.class);
        when(aggregator2.match(any())).thenReturn(ag2);

        VerticalAggregatorExecutorImpl aggregator3 =
                mock(VerticalAggregatorExecutorImpl.class);
        when(aggregator3.match(any())).thenReturn(ag3);

        List<AggregatorExecutor> mockedAggregators =
                Arrays.asList(aggregator1, aggregator2, aggregator3);

        when(aggregatorPoolService.getAggregators()).thenReturn(mockedAggregators);*/