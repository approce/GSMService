package com.service;

import com.service.interfaces.SMSLibService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.SMSLibException;
import org.smslib.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@org.springframework.stereotype.Service(value = "SIMLibService")
public class SMSLibServiceImpl implements SMSLibService {

    private static final Logger LOG = LoggerFactory.getLogger(SMSLibServiceImpl.class);
    private static final Service SERVICE = Service.getInstance();

    @PostConstruct
    @Override
    public void initialize() {
        LOG.debug("Start SMSLibService initialization");
        SERVICE.S.CONCURRENT_GATEWAY_START = false;
        SERVICE.S.SERIAL_POLLING = true;
        SERVICE.S.AT_WAIT_AFTER_RESET = 30;
    }

    @Override
    public Service getInstance() {
        return SERVICE;
    }

    @Override
    public void startService() {
        try {
            SERVICE.startService();
        } catch (SMSLibException e) {
            LOG.error("Exception while SMSLib service start.\n{}", e);
        } catch (IOException e) {
            LOG.error("Exception while SMSLib service start.\n{}", e);
        } catch (InterruptedException e) {
            LOG.error("Exception while SMSLib service start.\n{}", e);
        }
    }

    @Override
    public void stopService() {
        try {
            SERVICE.stopService();
        } catch (SMSLibException e) {
            LOG.error("Exception while SMSLib service stop.\n{}", e);
        } catch (IOException e) {
            LOG.error("Exception while SMSLib service start.\n{}", e);
        } catch (InterruptedException e) {
            LOG.error("Exception while SMSLib service start.\n{}", e);
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
