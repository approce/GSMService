package com.service;

import com.DAO.AggregatorDAO;
import com.service.interfaces.AggregatorService;
import config.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Context.class)
public class AggregatorServiceImplTest {

    @Autowired
    private AggregatorDAO aggregatorDAO;

    @Test
    public void testInitialization() throws Exception {
        //create manually aggregatorService, because on autowiring it will automatically set up.
        AggregatorService aggregatorService = new AggregatorServiceImpl();
        aggregatorService.setAggregatorDAO(aggregatorDAO);
        //aggregatorService.initialization();
    }
}