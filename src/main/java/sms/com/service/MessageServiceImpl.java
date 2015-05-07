package sms.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.DAO.MessageRepository;
import sms.com.DAO.interfaces.MessageDAO;
import sms.com.model.Message;
import sms.com.service.interfaces.MessageService;

import javax.annotation.PostConstruct;

@Service(value = "MessageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private MessageRepository messageRepository;

    @PostConstruct
    public void init(){
        Message message = new Message();
        message.setId(1l);
        message.setOriginator("asdas");
        messageRepository.save(message);
    }

    @Override
    public void save(Message message) {
        messageDAO.save(message);
    }

}
