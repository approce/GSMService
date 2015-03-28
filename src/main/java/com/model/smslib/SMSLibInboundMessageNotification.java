package com.model.smslib;

import com.DAO.interfaces.MessageDAO;
import com.model.Message;
import com.model.aggregator.Aggregator;
import com.service.interfaces.AggregatorService;
import org.smslib.AGateway;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * Created by Roman Zayats on 28.03.2015.
 */
@Component
public class SMSLibInboundMessageNotification implements IInboundMessageNotification {

    @Autowired
    private AggregatorService aggregatorService;

    @Autowired
    private MessageDAO messageDAO;

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

        for (Aggregator aggregator : aggregatorService.getAggregatorList()) {
            if (aggregator.getModem().equals(aGateway)) {
                message.setAggregator_id(aggregator.getId());
            }
        }
        messageDAO.saveMessage(message);
        //TODO delete message
    }
}
