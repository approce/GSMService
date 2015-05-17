package sms.com.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import sms.com.aggregators.AggregatorExecutor;
import sms.com.matcher.RequestMatcher;
import sms.com.model.Request;
import sms.com.model.Offer;
import sms.com.repository.RequestRepository;

import java.util.Calendar;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static sms.com.model.Request.RequestStatus.AVAILABLE;
import static sms.com.model.Request.RequestStatus.EXECUTING;

@RunWith(MockitoJUnitRunner.class)
public class RequestPoolServiceImplTest {

    @InjectMocks
    private RequestPoolServiceImpl requestPoolService = new RequestPoolServiceImpl();

    @Mock
    private Set<Request> available_request_set;

    @Mock
    private RequestMatcher requestMatcher;

    @Mock
    private AggregatorPoolService aggregatorPoolService;

    @Mock
    private RequestRepository requestRepository;

    private Request testRequest;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        testRequest = getRequest();
        when(requestMatcher.setMatchedAggregator(any(), any())).thenReturn(mock(AggregatorExecutor.class));
    }

    @Test
    public void testMatchedRequestSavedToDB() throws Exception {
        testRequest.setRequestStatus(EXECUTING);

        requestPoolService.add(testRequest);

        verify(requestRepository, times(2)).save(testRequest);
    }

    @Test
    public void testMatchedRequestRemovedFromSet() throws Exception {
        testRequest.setRequestStatus(EXECUTING);

        requestPoolService.add(testRequest);

        Set<Request> availableRequestSet = RequestPoolServiceImpl.getAvailableRequestSet();
        assertThat(availableRequestSet).doesNotContain(testRequest);
    }

    @Test
    public void testRequestHaveNotMatchedSavedToDBOnce() throws Exception {
        testRequest.setRequestStatus(AVAILABLE);

        requestPoolService.add(testRequest);

        verify(requestRepository, times(1)).save(testRequest);
    }

    @Test
    public void testRequestHaveNotMatchedStillInSet() throws Exception {
        testRequest.setRequestStatus(AVAILABLE);

        requestPoolService.add(testRequest);

        Set<Request> availableRequestSet = RequestPoolServiceImpl.getAvailableRequestSet();
        assertThat(availableRequestSet).contains(testRequest);
    }

    private Request getRequest() {
        Offer offer=new Offer();
        Request request = new Request();
        request.setID(123l);
        request.setCreate_date(Calendar.getInstance());
        request.setOffer(offer);
        return request;
    }
}
