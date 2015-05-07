package sms.com.DAO;

import sms.com.DAO.interfaces.MessageDAO;
import sms.com.model.Message;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("MessageDAO")
@Transactional
public class MessageDAOImpl extends GenericDAOImpl<Message> implements MessageDAO {

}
