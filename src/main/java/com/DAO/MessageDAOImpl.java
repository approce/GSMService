package com.DAO;

import com.DAO.interfaces.MessageDAO;
import com.model.Message;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("MessageDAO")
@Transactional
public class MessageDAOImpl extends HibernateDaoSupport implements MessageDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public void saveMessage(Message message) {
        getHibernateTemplate().save(message);
    }
}
