package com.service.interfaces;

import org.smslib.AGateway;
import org.smslib.Service;

public interface SMSLibService {

    void initialize();

    void startService();

    void stopService();

    void addGateway(AGateway gateway);
}
