package sms.com.aggregators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sms.com.model.SIM;
import sms.com.model.SIMProvider;
import sms.com.repository.ProviderRepository;
import sms.com.repository.SIMRepository;

import javax.annotation.PostConstruct;


public class SIMExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(SIMExecutor.class);

    private final String ID;

    @Autowired
    private SIMRepository simRepository;

    @Autowired
    private ProviderRepository providerRepository;

    private SIM currentSIM;

    private Integer providerId;

    private SIMProvider simProvider;

    public SIMExecutor(String id, Integer providerId) {
        ID = id;
        this.providerId = providerId;
    }

    @PostConstruct
    public void init() {
        simProvider = providerRepository.findOne(providerId);
        if(simProvider==null){
            //TODO throw exception.
        }
    }

    public void create(long number) {
        SIM sim = findSIMInDB(number);
        if(sim == null) {
            sim = createSIM(number);
        }
        this.currentSIM = sim;
    }

    public SIM getCurrentSIM() {
        return currentSIM;
    }

    private SIM createSIM(long number) {
        return new SIM(number, simProvider);
    }

    private SIM findSIMInDB(long number) {
        return simRepository.findOne(number);
    }
}