package sms.com.smslib;

import org.ajwcc.pduUtils.gsm3040.PduUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.IUSSDNotification;
import org.smslib.USSDResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sms.com.aggregators.AbstractAggregatorFacade;
import sms.com.service.AggregatorPoolService;
import sms.com.utils.StringMethods;

@Component
public class SMSLibUSSDNotification implements IUSSDNotification {

    private static final Logger LOG = LoggerFactory.getLogger(SMSLibUSSDNotification.class);

    @Autowired
    private AggregatorPoolService aggregatorPoolService;

    @Override
    public void process(AGateway aGateway, USSDResponse ussdResponse) {
        AbstractAggregatorFacade aggregator = aggregatorPoolService.getAggregatorByGateway(aGateway.getGatewayId());
        try {
            String body = getBody(ussdResponse);
            long number = StringMethods.findLongNumber(body);
            aggregator.processUSSDResponse(number);
            LOG.debug("Gateway ID: {}. Received USSD response:\n{}", aGateway.getGatewayId(), body);
        } catch(Exception e) {
            LOG.error("Exception while process USSD response.\n{}", e);
        }
    }

    private String getBody(USSDResponse ussdResponse) throws Exception {
        String bodyDecoded = StringMethods.find(ussdResponse.getRawResponse(), "\"", "\"");
        return PduUtils.decodeUcs2Encoding(null, PduUtils.pduToBytes(bodyDecoded));
    }
}
