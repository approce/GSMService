package sms.com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.aggregators.AggregatorExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.Map;

@Service
public class AggregatorPoolServiceImpl implements AggregatorPoolService {

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorPoolServiceImpl.class);

    @Autowired
    private SMSLibService smsLibService;

    @Autowired
    @Resource(name = "aggregatorsMap")
    private Map<String, AggregatorExecutor> AGGREGATORS_MAP;

    @PostConstruct
    public void initialize() {
        LOG.debug("Start AggregatorService initialization");
        initializeGateways();
        //TODO remove start from here.
//        smsLibService.startService();
    }

    @Override
    public LinkedList<AggregatorExecutor> getAggregators() {
        return new LinkedList<>(AGGREGATORS_MAP.values());
    }

    @Override
    public AggregatorExecutor getAggregator(String id) {
        return AGGREGATORS_MAP.get(id);
    }

    private void initializeGateways() {
        AGGREGATORS_MAP.values().stream().filter(ae -> ae.START_ON_SETUP)
                       .forEach(ae -> smsLibService.addGateway(ae.modemExecutor.getModem()));
    }
}
