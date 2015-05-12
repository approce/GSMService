package sms.com.aggregators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sms.com.model.SIM;
import sms.com.model.SIMCell;
import sms.com.repository.SIMCellRepository;
import sms.com.repository.SIMRepository;

import javax.annotation.PostConstruct;


public class SIMExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(SIMExecutor.class);

    private final String ID;

    @Autowired
    private SIMRepository simRepository;

    @Autowired
    private SIMCellRepository simCellRepository;

    private SIMCell simCell;

    private String simCellId;

    private String getNumberCode;

    private SIM currentSIM;

    public SIMExecutor(String id, String simCellId) {
        ID = id;
        this.simCellId = simCellId;
    }

    @PostConstruct
    public void init() {
        this.simCell = simCellRepository.findOne(simCellId);
        if(simCell == null) {
            //TODO throw exception.
        } else {
            this.getNumberCode = simCell.getSimProvider().getGetNumberUSSD();
        }
        LOG.trace("SIMExecutor ID: {} have been successfully initialized.", ID);
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

    public String getGetNumberCode() {
        return getNumberCode;
    }

    private SIM createSIM(long number) {
        return new SIM(number, simCell.getSimProvider());
    }

    private SIM findSIMInDB(long number) {
        return simRepository.findOne(number);
    }
}