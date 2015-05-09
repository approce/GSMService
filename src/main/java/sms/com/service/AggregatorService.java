package sms.com.service;

import org.smslib.AGateway;
import org.smslib.IGatewayStatusNotification;
import sms.com.aggregators.AggregatorExecutor;
import sms.com.model.Message;

import java.util.List;

public interface AggregatorService extends IGatewayStatusNotification{

    List<AggregatorExecutor> getAggregators();

    void processInboundMessage(Message message, AGateway gateway);

    void processUSSDNotification(String notification, AGateway gateway);

}
