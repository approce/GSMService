package com.service.interfaces;

import org.smslib.AGateway;

public interface SMSLibService {

    void initialize();

    void addGateway(AGateway gateway);
}
