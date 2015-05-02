package com.service;

import com.model.Message;
import com.aggregators.AggregatorExecutor;
import com.model.Modem;
import com.service.interfaces.AggregatorService;
import com.service.interfaces.SMSLibService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AggregatorServiceImpl implements AggregatorService {

    @Autowired
    private SMSLibService smsLibService;

    @Autowired
    @Resource(name = "aggregatorsMap")
    private Map<String, AggregatorExecutor> AGGREGATORS_MAP;

    @PostConstruct
    public void dos() {
        System.out.println("asd");
    }

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorServiceImpl.class);

    @Override
    public void processInboundMessage(Message message, AGateway gateway) {

    }

    @Override
    public void processUSSDNotification(String notification, AGateway gateway) {

    }

    @Override
    public void processStatusNotification(GatewayStatuses newStatus, GatewayStatuses oldStatus,
                                          AGateway gateway) {
        if (newStatus.equals(GatewayStatuses.STARTED)) {
            AGGREGATORS_MAP.get(gateway.getGatewayId()).sendGetNumberUSSD();
        }
    }
}
