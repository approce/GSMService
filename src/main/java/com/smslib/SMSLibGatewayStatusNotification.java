package com.smslib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.IGatewayStatusNotification;
import org.springframework.stereotype.Component;

@Component
public class SMSLibGatewayStatusNotification implements IGatewayStatusNotification {

    private static final Logger LOG = LoggerFactory.getLogger(SMSLibGatewayStatusNotification.class);

    @Override
    public void process(AGateway aGateway, AGateway.GatewayStatuses oldStatus, AGateway.GatewayStatuses newStatus) {
        LOG.debug("Gateway id:" + aGateway.getGatewayId() + " status " + oldStatus + " changed to " + newStatus);
    }
}
