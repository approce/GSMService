package com.DAO;

import com.DAO.interfaces.MessageDAO;
import com.model.Message;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("MessageDAO")
@Transactional
public class MessageDAOImpl extends GenericDAOImpl<Message> implements MessageDAO {

}
