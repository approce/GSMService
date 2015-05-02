package com.service.interfaces;

import com.model.Message;
import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;

public interface AggregatorService {

    void processInboundMessage(Message message, AGateway gateway);

    void processUSSDNotification(String notification, AGateway gateway);

    void processStatusNotification(GatewayStatuses newStatus, AGateway gateway);
}
