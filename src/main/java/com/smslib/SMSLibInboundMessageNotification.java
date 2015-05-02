package com.smslib;

import com.model.Message;
import com.service.interfaces.AggregatorService;
import com.service.interfaces.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class SMSLibInboundMessageNotification implements IInboundMessageNotification {

    @Autowired
    private AggregatorService aggregatorService;

    //TODO move it from here:
    @Autowired
    private MessageService messageService;

    private static final Logger LOG = LoggerFactory.getLogger(SMSLibInboundMessageNotification.class);

    @Override
    public void process(AGateway aGateway, org.smslib.Message.MessageTypes messageTypes, InboundMessage inboundMessage) {
        if (!messageTypes.equals(org.smslib.Message.MessageTypes.INBOUND)) {
            return;
        }

        Message message = new Message();
        message.setOriginator(inboundMessage.getOriginator());
        message.setText(inboundMessage.getText());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inboundMessage.getDate());
        message.setDate(calendar);
            //TODO move this to aggregator service:
//            message.setAggregator_id(aggregatorService.getAggregatorExecutorByGateway(aGateway).getAggregatorId());
//            messageService.save(message);

        try {
            aGateway.deleteMessage(inboundMessage);
        } catch (Exception e) {
            LOG.error("Exception while deleting inbound message.\n{}", e);
        }

        aggregatorService.processInboundMessage(message, aGateway);
    }
}
