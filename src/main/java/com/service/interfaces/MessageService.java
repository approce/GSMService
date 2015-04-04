package com.service.interfaces;

import com.model.Message;

public interface MessageService {

    void save(Message message);

    Message findById(long id);
}