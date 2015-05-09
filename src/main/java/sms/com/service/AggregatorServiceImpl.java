package sms.com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.aggregators.AggregatorExecutor;
import sms.com.model.Message;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.Map;

@Service
public class AggregatorServiceImpl implements AggregatorService {

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorServiceImpl.class);

    @Autowired
    private SMSLibService smsLibService;

    @Autowired
    @Resource(name = "aggregatorsMap")
    private Map<String, AggregatorExecutor> AGGREGATORS_MAP;

    @PostConstruct
    public void initialize() {
        LOG.debug("Start AggregatorService initialization");
        initializeGateways();
        smsLibService.startService();
    }

    @Override
    public LinkedList<AggregatorExecutor> getAggregators() {
        return new LinkedList<>(AGGREGATORS_MAP.values());
    }

    @Override
    public void processInboundMessage(Message message, AGateway gateway) {

    }

    @Override
    public void processUSSDNotification(String notification, AGateway gateway) {

    }

    @Override
    public void processStatusNotification(GatewayStatuses newStatus, GatewayStatuses oldStatus,
                                          AGateway gateway) {
        if(newStatus.equals(GatewayStatuses.STARTED)) {
            AGGREGATORS_MAP.get(gateway.getGatewayId()).sendGetNumberUSSD();
        }
    }

    private void initializeGateways() {
        AGGREGATORS_MAP.values().stream().filter(ae -> ae.START_ON_SETUP)
                       .forEach(ae -> smsLibService.addGateway(ae.MODEM));
    }
}
