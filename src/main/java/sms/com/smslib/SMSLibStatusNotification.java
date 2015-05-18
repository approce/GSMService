package sms.com.smslib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.IGatewayStatusNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sms.com.aggregators.AggregatorFacade;
import sms.com.service.AggregatorPoolService;

import static org.smslib.AGateway.GatewayStatuses;

@Component
public class SMSLibStatusNotification implements IGatewayStatusNotification {

    private static final Logger LOG =
            LoggerFactory.getLogger(SMSLibStatusNotification.class);

    @Autowired
    private AggregatorPoolService aggregatorPoolService;

    @Override
    public void process(AGateway aGateway, GatewayStatuses oldStatus, GatewayStatuses newStatus) {
        LOG.debug("Gateway ID: {} changed status from {} to {}\n", aGateway.getGatewayId(),
                  oldStatus, newStatus);

        AggregatorFacade aggregator = aggregatorPoolService.getAggregatorByGateway(aGateway.getGatewayId());
        aggregator.processStatus(oldStatus, newStatus);
    }
}
