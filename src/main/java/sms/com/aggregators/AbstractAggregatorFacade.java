package sms.com.aggregators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway.GatewayStatuses;
import org.springframework.stereotype.Component;
import sms.com.matcher.RequestMatch;
import sms.com.model.Message;
import sms.com.model.Modem;
import sms.com.model.Request;
import sms.com.modem.ModemExecutor;

import static sms.com.aggregators.AggregatorStatus.SIM_DEFINED;

@Component
public abstract class AbstractAggregatorFacade {

    protected static final Logger LOG = LoggerFactory.getLogger(AbstractAggregatorFacade.class);

    private final String id;

    private final Boolean startOnSetup;

    private ModemExecutor modemExecutor;

    private SIMExecutor simExecutor;

    private AggregatorRequestExecutor aggregatorRequestExecutor;

    private AggregatorStatus status;

    public AbstractAggregatorFacade(String id, Boolean startOnSetup) {
        this.id = id;
        this.startOnSetup = startOnSetup;
    }

    public RequestMatch match(Request request) {
        return aggregatorRequestExecutor.matchRequest(request, simExecutor.getCurrentSIM(), this);
    }

    public void processMessage(Message message) {
        message.setSim(simExecutor.getCurrentSIM());

        aggregatorRequestExecutor.matchMessageWithRequest(message);
    }

    public void processStatus(GatewayStatuses oldStatus, GatewayStatuses newStatus) {
        if(oldStatus.equals(GatewayStatuses.STARTING) && newStatus.equals(GatewayStatuses.STARTED)) {
            startInitialization();
        }
    }

    public void processUSSDResponse(long number) {
        simExecutor.create(number);
        status = SIM_DEFINED;
        LOG.trace("Gateway ID: {}. SIM successfully initialized. Status: {}.", id, status);
    }

    public void setModemExecutor(ModemExecutor modemExecutor) {
        this.modemExecutor = modemExecutor;
    }

    public void setSimExecutor(SIMExecutor simExecutor) {
        this.simExecutor = simExecutor;
    }

    public void setAggregatorRequestExecutor(AggregatorRequestExecutor aggregatorRequestExecutor) {
        this.aggregatorRequestExecutor = aggregatorRequestExecutor;
    }

    public void addRequest(Request request) {
        aggregatorRequestExecutor.addRequest(request);
    }

    public Modem getModem() {
        return modemExecutor.getModem();
    }

    public Boolean getStartOnSetup() {
        return startOnSetup;
    }

    public String getId() {
        return id;
    }

    private void startInitialization() {
        modemExecutor.sendGetNumberUSSD();
    }
}


