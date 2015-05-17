package sms.com.service;

import sms.com.model.Message;

public interface MessageService {

    void save(Message message);

    void process(Message message);
}