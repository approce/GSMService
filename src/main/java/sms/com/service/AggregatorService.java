package sms.com.service;

import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import sms.com.aggregators.AggregatorExecutor;
import sms.com.model.Message;

import java.util.List;

public interface AggregatorService {

    List<AggregatorExecutor> getAggregators();

    void processInboundMessage(Message message, AGateway gateway);

    void processUSSDNotification(String notification, AGateway gateway);

    void processStatusNotification(GatewayStatuses newStatus, GatewayStatuses oldStatus,
                                   AGateway gateway);
}
