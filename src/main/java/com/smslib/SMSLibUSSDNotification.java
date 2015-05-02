package com.smslib;

import com.service.interfaces.AggregatorService;
import com.utils.StringMethods;
import org.ajwcc.pduUtils.gsm3040.PduUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.IUSSDNotification;
import org.smslib.USSDResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SMSLibUSSDNotification implements IUSSDNotification {

    private static final Logger LOG = LoggerFactory.getLogger(SMSLibUSSDNotification.class);

    @Autowired
    private AggregatorService aggregatorService;

    @Override
    public void process(AGateway aGateway, USSDResponse ussdResponse) {
        String body = null;
        try {
            String bodyDecoded = StringMethods.find(ussdResponse.getRawResponse(), "\"", "\"");
            body = PduUtils.decodeUcs2Encoding(null, PduUtils.pduToBytes(bodyDecoded));
            LOG.debug("USSD response received gateway ID: {}\n{}", aGateway.getGatewayId(), body);
        } catch (Exception e) {
            LOG.error("Exception while USSD find body {}", e);
        }

        aggregatorService.processUSSDNotification(body, aGateway);
    }
}
