package sms.com.aggregators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway.GatewayStatuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sms.com.model.Message;
import sms.com.model.Modem;
import sms.com.model.Request;
import sms.com.model.SIM;
import sms.com.model.SIMCell;
import sms.com.model.SimFactory;
import sms.com.modem.ModemExecutor;
import sms.com.repository.SIMCellRepository;
import sms.com.repository.SIMRepository;
import sms.com.utils.SMSLibUtils;
import sms.com.utils.StringMethods;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

import static sms.com.aggregators.AggregatorStatus.FAILED_TO_START;
import static sms.com.aggregators.AggregatorStatus.NUMBER_ASSIGNED;

@Component
public abstract class AggregatorExecutor {

    protected static final Logger LOG = LoggerFactory.getLogger(AggregatorExecutor.class);

    public final Boolean START_ON_SETUP;

    public final ModemExecutor modemExecutor;

    public AggregatorStatus status;

    protected SIMCell simCell;

    protected SIM currentSIM;

    private final String ID;

    private final Set<Request> requests = new HashSet<>();

    private SimFactory simFactory;

    private String simCellId;

    @Autowired
    private SIMCellRepository simCellRepository;

    @Autowired
    private SIMRepository simRepository;

    public AggregatorExecutor(Boolean startOnSetup, Modem modem, String simCell) {
        this.ID = modem.getGatewayId();
        this.START_ON_SETUP = startOnSetup;
        this.modemExecutor = new ModemExecutor(modem);
        this.simCellId = simCell;
    }

    @PostConstruct
    public void init() {
        simCell = simCellRepository.findOne(simCellId);
        if(simCell == null) {
            LOG.error("Aggregator:{}. Wrong sim cell assigned:{}", ID, simCellId);
            this.status = FAILED_TO_START;
            return;
        }
        String getNumberUSSDCommand = simCell.getSimProvider().getGetNumberUSSD();
        getNumberUSSDCommand = SMSLibUtils.convertGetNumberCommand(getNumberUSSDCommand);
        modemExecutor.setGetNumberUSSDCommand(getNumberUSSDCommand);

        this.simFactory = new SimFactory(this.simCell.getSimProvider());
    }

    public abstract double match(Request request);

    public void startInitialization() {
        modemExecutor.sendGetNumberUSSD();
    }

    public void processStatus(GatewayStatuses oldStatus, GatewayStatuses newStatus) {
        if(oldStatus.equals(GatewayStatuses.STARTING) && newStatus.equals(GatewayStatuses.STARTED)) {
            startInitialization();
        }
    }

    public void processUSSDResponse(String body) {
        long number = StringMethods.findLongNumber(body);
        setCurrentSim(number);
        status = NUMBER_ASSIGNED;
    }

    public void processMessage(Message message) {

    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    private void setCurrentSim(long number) {
        currentSIM = simRepository.findOne(number);
        if(currentSIM == null) {
            currentSIM = simFactory.createSIM(number);
        }
    }
}
