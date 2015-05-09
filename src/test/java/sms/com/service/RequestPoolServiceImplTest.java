package sms.com.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import sms.com.aggregators.AggregatorExecutor;
import sms.com.aggregators.VerticalAggregatorExecutorImpl;
import sms.com.model.Request;
import sms.com.model.ServiceModel;
import sms.com.repository.RequestRepository;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RequestPoolServiceImplTest {

    @InjectMocks
    private RequestPoolServiceImpl requestPoolService = new RequestPoolServiceImpl();

    @Mock
    private AggregatorService aggregatorService;

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private Set<Request> available_request_set;

    @Test
    public void testRequestSavedToDB() throws Exception {
        initMocks(0.0, 1.1, 1.3);
        Request request = getRequest();

        requestPoolService.add(request);

        verify(requestRepository, times(2)).save(request);
    }

    @Test
    public void testRequestRemovedFromSet() throws Exception {
        initMocks(0.0, 1.1, 1.3);
        Request request = getRequest();

        requestPoolService.add(request);

        Set<Request> availableRequestSet = RequestPoolServiceImpl.getAvailableRequestSet();

        assertThat(availableRequestSet).doesNotContain(request);
    }

    @Test
    public void testRequestHaveNotMatchedSavedToDBOnce() throws Exception {
        initMocks(0.0, 0.0, 0.0);
        Request request = getRequest();

        requestPoolService.add(request);

        verify(requestRepository, times(1)).save(request);
    }

    @Test
    public void testRequestHaveNotMatchedStillInSet() throws Exception {
        initMocks(0.0, 0.0, 0.0);
        Request request = getRequest();

        requestPoolService.add(request);

        Set<Request> availableRequestSet = RequestPoolServiceImpl.getAvailableRequestSet();

        assertThat(availableRequestSet).contains(request);
    }

    private Request getRequest() {
        Request request = new Request();
        request.setID(123l);
        request.setCreate_date(Calendar.getInstance());
        request.setService(new ServiceModel());
        return request;
    }

    private void initMocks(double ag1, double ag2, double ag3) {
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

        when(aggregatorService.getAggregators()).thenReturn(mockedAggregators);
    }
}