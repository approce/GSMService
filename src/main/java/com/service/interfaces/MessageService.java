package com.service.interfaces;

import com.model.Message;

public interface MessageService {

    void save(Message message);

    public Message findById(long id);
}