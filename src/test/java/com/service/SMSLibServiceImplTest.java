package com.service;

import com.service.interfaces.AggregatorService;
import config.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.smslib.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Context.class)
public class SMSLibServiceImplTest {

    @Autowired
    private AggregatorService aggregatorService;

    @Autowired
    private SMSLibServiceImpl smsLibService;

    @Test
    public void stopServiceTest() throws Exception {
        if (smsLibService.getInstance().getServiceStatus().equals(Service.ServiceStatus.STOPPED)) {
            smsLibService.startService();
        }
        smsLibService.stopService();
        assertEquals(smsLibService.getInstance().getServiceStatus(), Service.ServiceStatus.STOPPED);
    }

    @Test
    public void startServiceTest() throws Exception {
        if (!smsLibService.getInstance().getServiceStatus().equals(Service.ServiceStatus.STOPPED)) {
            smsLibService.stopService();
        }
        smsLibService.startService();
        assertEquals(smsLibService.getInstance().getServiceStatus(), Service.ServiceStatus.STARTED);
    }
}