package com.DAO;

import com.model.Modem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository("ModemDAO")
@Transactional
public class ModemDAOImplDB extends HibernateDaoSupport implements ModemDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public List<Modem> getModems() {
        return (List<Modem>) getHibernateTemplate().find("from Modem");
    }
}
