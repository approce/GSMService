package com.service;

import com.service.interfaces.AggregatorService;
import config.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Context.class)
public class AggregatorServiceImplTest {

    @Autowired
    private AggregatorService aggregatorService;

    @Test
    public void testInitialization() throws Exception {
        assertTrue("Wrong path to aggregator.xml file or wrong aggregator definition. " +
                        "There should be at least one aggregator",
                aggregatorService.getAggregatorList().size() > 0);
    }
}