package com.service;

import com.model.Message;
import com.model.aggregator.AggregatorExecutor;
import com.service.interfaces.AggregatorService;
import com.service.interfaces.SMSLibService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AggregatorServiceImpl implements AggregatorService {

    @Autowired
    private SMSLibService smsLibService;
    @Autowired
    private List<AggregatorExecutor> aggregatorExecutors;

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorServiceImpl.class);
    private static List<AggregatorExecutor> AGGREGATOR_LIST;

    public AggregatorExecutor getAggregatorExecutorByGateway(AGateway gateway) throws Exception {
        for (AggregatorExecutor aggregatorExecutor : AGGREGATOR_LIST) {
            if (aggregatorExecutor.MODEM.equals(gateway)) {
                return aggregatorExecutor;
            }
        }
        throw new Exception();
    }

    @Override
    public void proccessInboundMessage(Message message, AGateway gateway) {

    }

    @Override
    public void proccessUSSDNotification(String notification, AGateway gateway) {

    }

    @Override
    public void proccessStatusNotification(AGateway.GatewayStatuses oldStatus, AGateway.GatewayStatuses newStatus, AGateway gateway) {

    }
}
