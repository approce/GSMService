package com.service;

import com.service.interfaces.SMSLibService;
import com.smslib.SMSLibGatewayStatusNotification;
import com.smslib.SMSLibInboundMessageNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@org.springframework.stereotype.Service(value = "SIMLibService")
public class SMSLibServiceImpl implements SMSLibService {

    @Autowired
    private SMSLibInboundMessageNotification smsLibInboundMessageNotification;

    @Autowired
    private SMSLibGatewayStatusNotification smsLibGatewayStatusNotification;

    private static final Logger LOG = LoggerFactory.getLogger(SMSLibServiceImpl.class);
    private static final Service SERVICE = Service.getInstance();

    @PostConstruct
    @Override
    public void initialize() {
        LOG.debug("Start SMSLibService initialization");
        SERVICE.S.CONCURRENT_GATEWAY_START = false;
        SERVICE.S.SERIAL_POLLING = true;
        SERVICE.S.AT_WAIT_AFTER_RESET = 30;

        SERVICE.setInboundMessageNotification(smsLibInboundMessageNotification);
        SERVICE.setGatewayStatusNotification(smsLibGatewayStatusNotification);

    }

    @Override
    public Service getInstance() {
        return SERVICE;
    }

    @Override
    public void startService() {
        try {
            SERVICE.startService();
        } catch (Exception e) {
            LOG.error("Exception while SMSLib service start.\n{}", e);
        }
    }

    @Override
    public void stopService() {
        try {
            SERVICE.stopService();
        } catch (Exception e) {
            LOG.error("Exception while SMSLib service stop.\n{}", e);
        }
    }

    @Override
    public void addGateway(AGateway gateway) {
        LOG.debug("Add gateway {}", gateway.getGatewayId());
        try {
            SERVICE.addGateway(gateway);
        } catch (GatewayException e) {
            LOG.error("Exception while gateway adding.\n{}", e);
        }
    }
}
