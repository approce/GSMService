package com.service.interfaces;

import org.smslib.AGateway;

public interface SMSLibService {

    void initialize();

    void startService();

    void stopService();

    void addGateway(AGateway gateway);
}
