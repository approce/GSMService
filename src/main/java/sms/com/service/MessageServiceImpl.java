package sms.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.repository.MessageRepository;
import sms.com.model.Message;

@Service(value = "MessageService")
public class MessageServiceImpl implements MessageService {

    @Autowired private MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

}
