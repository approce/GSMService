package sms.com.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.smslib.Service;
import org.smslib.Settings;
import sms.com.aggregators.AbstractAggregatorFacade;
import sms.com.aggregators.VerticalAggregatorImpl;
import sms.com.model.Modem;
import sms.com.smslib.SMSLibInboundMessageNotification;
import sms.com.smslib.SMSLibStatusNotification;
import sms.com.smslib.SMSLibUSSDNotification;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SMSLibServiceImplTest {

    @InjectMocks
    private SMSLibServiceImpl smsLibService;

    @Mock
    private Service service;

    @Mock
    private SMSLibInboundMessageNotification smsLibInboundMessageNotification;

    @Mock
    private SMSLibUSSDNotification smsLibUSSDNotification;

    @Mock
    private SMSLibStatusNotification smsLibStatusNotification;

    @Mock
    private AggregatorPoolServiceImpl aggregatorPoolService;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        Settings settings = mock(Settings.class);
        when(service.getSettings()).thenReturn(settings);
    }

    @Test
    public void testAddGateways() throws Exception {
        prepareAggregatorPool();

        smsLibService.initialize();

        verify(service, times(2)).addGateway(any());
    }

    private void prepareAggregatorPool() {
        VerticalAggregatorImpl aggregator1 =
                mock(VerticalAggregatorImpl.class);
        when(aggregator1.getStartOnSetup()).thenReturn(true);

        Modem modem1 = mock(Modem.class);
        when(aggregator1.getModem()).thenReturn(modem1);

        VerticalAggregatorImpl aggregator2 =
                mock(VerticalAggregatorImpl.class);
        when(aggregator2.getStartOnSetup()).thenReturn(false);

        VerticalAggregatorImpl aggregator3 =
                mock(VerticalAggregatorImpl.class);
        when(aggregator3.getStartOnSetup()).thenReturn(true);

        Modem modem2 = mock(Modem.class);
        when(aggregator3.getModem()).thenReturn(modem2);

        List<AbstractAggregatorFacade> mockedAggregators =
                Arrays.asList(aggregator1, aggregator2, aggregator3);

        when(aggregatorPoolService.getAggregators()).thenReturn(mockedAggregators);
    }

}