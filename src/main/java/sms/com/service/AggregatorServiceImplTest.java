package sms.com.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sms.com.config.Context;
import sms.com.service.interfaces.AggregatorService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Context.class)
public class AggregatorServiceImplTest {

    @Autowired
    private AggregatorService aggregatorService;

    @Test
    public void testInitialization() throws Exception {
    }
}