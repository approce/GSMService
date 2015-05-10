package sms.com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.aggregators.AggregatorExecutor;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AggregatorPoolServiceImpl implements AggregatorPoolService {

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorPoolServiceImpl.class);

    @Autowired
    @Resource(name = "aggregatorsMap")
    private Map<String, AggregatorExecutor> AGGREGATORS_MAP;

    @Override
    public List<AggregatorExecutor> getAggregators() {
        return new LinkedList<>(AGGREGATORS_MAP.values());
    }

    @Override
    public AggregatorExecutor getAggregator(String id) {
        return AGGREGATORS_MAP.get(id);
    }
}
