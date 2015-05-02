package com.service;

import com.DAO.interfaces.MessageDAO;
import com.model.Message;
import com.service.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service(value = "MessageService")
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public void save(Message message) {
        messageDAO.save(message);
    }

}
