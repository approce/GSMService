package com.DAO;

import com.DAO.interfaces.AggregatorExecutorDAO;
import com.model.aggregator.AggregatorExecutor;
import com.model.aggregator.VerticalAggregator;
import com.model.aggregator.VerticalAggregatorExecutorImpl;
import com.utils.xml.XMLConverter;
import com.utils.xml.XMLListWrapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("AggregatorDAO")
@Transactional
public class AggregatorExecutorDAOXMLImpl extends HibernateDaoSupport implements AggregatorExecutorDAO {

    private XMLConverter<AggregatorExecutor> converter =
            new XMLConverter<AggregatorExecutor>(XMLListWrapper.class, VerticalAggregatorExecutorImpl.class, VerticalAggregator.class);

    @Value("${aggregator.config.path}")
    private String CONFIG_PATH;

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public List<AggregatorExecutor> getAggregatorExecutors() {
        return converter.doUnmarshall(CONFIG_PATH);
    }
}
