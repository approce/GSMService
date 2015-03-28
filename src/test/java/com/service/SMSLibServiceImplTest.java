package com.service;

import com.model.aggregator.Aggregator;
import com.service.interfaces.AggregatorService;
import config.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.smslib.AGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Context.class)
public class SMSLibServiceImplTest {

    @Autowired
    private AggregatorService aggregatorService;

    @Test
    public void startGatewayTest() throws Exception {
        //TODO add test
    }
}