package sms.com.smslib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sms.com.model.Message;
import sms.com.service.MessageService;

import java.util.Calendar;

@Component
public class SMSLibInboundMessageNotification implements IInboundMessageNotification {

    private static final Logger LOG = LoggerFactory.getLogger(SMSLibInboundMessageNotification.class);

    @Autowired
    private MessageService messageService;

    @Override
    public void process(AGateway aGateway, MessageTypes messageType, InboundMessage inboundMessage) {
        if(!messageType.equals(MessageTypes.INBOUND)) {
            LOG.warn("Fail to process message. MessageType:{}", messageType);
            return;
        }

        Message message = convertMessage(aGateway, inboundMessage);

        messageService.process(message);

        //        deleteMessage(aGateway, inboundMessage);

        LOG.debug("Message ID:{} have been successfully processed.", message.getId());
    }

    private Message convertMessage(AGateway aGateway, InboundMessage inboundMessage) {
        Message message = new Message();
        message.setOriginator(inboundMessage.getOriginator());
        message.setBody(inboundMessage.getText());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inboundMessage.getDate());
        message.setReceipt_date(calendar);
        message.setAggregator_id(aGateway.getGatewayId());
        return message;
    }

    private void deleteMessage(AGateway aGateway, InboundMessage inboundMessage) {
        try {
            aGateway.deleteMessage(inboundMessage);
        } catch(Exception e) {
            LOG.error("Exception while deleting inbound message.\n{}", e);
        }
    }
}
