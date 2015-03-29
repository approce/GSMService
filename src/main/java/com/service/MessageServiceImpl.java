package com.service;

import com.DAO.interfaces.MessageDAO;
import com.model.Message;
import com.service.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "MessageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

}
