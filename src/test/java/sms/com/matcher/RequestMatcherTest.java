package sms.com.matcher;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sms.com.aggregators.AbstractAggregatorFacade;
import sms.com.aggregators.VerticalAggregatorImpl;
import sms.com.model.Request;
import sms.com.model.Request.RequestStatus;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestMatcherTest {

    @Mock
    private RequestMatchComparator comparator;

    @InjectMocks
    private RequestMatcher requestMatcher;

    private List<AbstractAggregatorFacade> abstractAggregatorFacades;

    private VerticalAggregatorImpl aggregator1;

    private VerticalAggregatorImpl aggregator2;

    private RequestMatch requestMatch1 = new RequestMatch();

    private RequestMatch requestMatch2 = new RequestMatch();

    private Request request;

    private String aggregator_1_id = "Aggregator_1";

    private String aggregator_2_id = "Aggregator_2";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(comparator.compare(requestMatch1, requestMatch2)).thenReturn(-1);
        request = new Request();
        request.setRequestStatus(RequestStatus.AVAILABLE);
        aggregator1 = mock(VerticalAggregatorImpl.class);
        aggregator2 = mock(VerticalAggregatorImpl.class);

        requestMatch1.setCanBeExecuted(true);
        requestMatch1.setMatch_index(1.2);
        requestMatch1.setAbstractAggregatorFacade(aggregator1);

        requestMatch2.setCanBeExecuted(true);
        requestMatch2.setMatch_index(1.1);
        requestMatch2.setAbstractAggregatorFacade(aggregator2);

        abstractAggregatorFacades = new LinkedList<>();

        when(aggregator1.match(request)).thenReturn(requestMatch1);
        when(aggregator2.match(request)).thenReturn(requestMatch2);
        when(aggregator1.getId()).thenReturn(aggregator_1_id);
        when(aggregator2.getId()).thenReturn(aggregator_2_id);

        abstractAggregatorFacades.add(aggregator1);
        abstractAggregatorFacades.add(aggregator2);
    }

    @Test
    public void testSetBestMatcherAggregator() throws Exception {
        AbstractAggregatorFacade
                abstractAggregatorFacade = requestMatcher.setMatchedAggregator(abstractAggregatorFacades, request);

        assertThat(abstractAggregatorFacade.getId()).isEqualTo(aggregator_1_id);

    }

    @Test
    public void testAllFieldsToRequestAreAssigned() throws Exception {
        requestMatcher.setMatchedAggregator(abstractAggregatorFacades, request);

        assertThat(request.getAggregator_id()).isEqualTo(aggregator_1_id);
        assertThat(request.getRequestStatus()).isEqualTo(RequestStatus.EXECUTING);
        assertThat(request.getStart_date()).isNotNull();
    }

    @Test
    public void testNotMatchedRequest() throws Exception {
        requestMatch1.setCanBeExecuted(false);
        requestMatch2.setCanBeExecuted(false);

        requestMatcher.setMatchedAggregator(abstractAggregatorFacades, request);

        assertThat(request.getRequestStatus()).isEqualTo(RequestStatus.AVAILABLE);
        assertThat(request.getAggregator_id()).isNull();
    }
}