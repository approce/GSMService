package sms.com.smslib;

import sms.com.service.interfaces.AggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.IGatewayStatusNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.smslib.AGateway.GatewayStatuses;

@Component
public class SMSLibGatewayStatusNotification implements IGatewayStatusNotification {

    private static final Logger LOG =
            LoggerFactory.getLogger(SMSLibGatewayStatusNotification.class);

    @Autowired
    private AggregatorService aggregatorService;

    @Override
    public void process(AGateway aGateway, GatewayStatuses oldStatus, GatewayStatuses newStatus) {
        LOG.debug("Gateway ID: {} changed status from {} to {}\n", aGateway.getGatewayId(),
                oldStatus, newStatus);

        aggregatorService.processStatusNotification(newStatus, oldStatus, aGateway);
    }

}
