package com.service;

import org.smslib.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Roman Zayats on 27.03.2015.
 */
//@org.springframework.stereotype.Service(value = "SMSLibService")
public class SMSLibService {

    //private static final Service SERVICE = Service.getInstance();

    @PostConstruct
    public void init() {
//        SERVICE.setInboundMessageNotification();
//        SERVICE.setUSSDNotification();
//        SERVICE.setGatewayStatusNotification();
//        SERVICE.S.CONCURRENT_GATEWAY_START = false;
//        SERVICE.S.SERIAL_POLLING = true;
//        SERVICE.S.AT_WAIT_AFTER_RESET = 30;
    }

}
