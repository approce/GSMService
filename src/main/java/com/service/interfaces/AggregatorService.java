package com.service.interfaces;

import com.model.Message;
import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;

public interface AggregatorService {

    void proccessInboundMessage(Message message, AGateway gateway);

    void proccessUSSDNotification(String notification, AGateway gateway);

    void proccessStatusNotification(GatewayStatuses oldStatus, GatewayStatuses newStatus, AGateway gateway);
}
