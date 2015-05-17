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
import sms.com.utils.StringMethods;

import static sms.com.aggregators.AggregatorStatus.SIM_DEFINED;


@Component
public abstract class AggregatorExecutor {

    protected static final Logger LOG = LoggerFactory.getLogger(AggregatorExecutor.class);

    private final String ID;

    private final Boolean startOnSetup;

    private final ModemExecutor modemExecutor;

    private final SIMExecutor simExecutor;

    private final AggregatorRequestExecutor aggregatorRequestExecutor;

    private AggregatorStatus status;

    public AggregatorExecutor(String id, Boolean startOnSetup, ModemExecutor modemExecutor, SIMExecutor simExecutor,
                              AggregatorRequestExecutor aggregatorRequestExecutor) {
        this.ID = id;
        this.startOnSetup = startOnSetup;
        this.modemExecutor = modemExecutor;
        this.simExecutor = simExecutor;
        this.aggregatorRequestExecutor = aggregatorRequestExecutor;
    }

    public RequestMatch match(Request request) {
        return aggregatorRequestExecutor.matchRequest(request, simExecutor.getCurrentSIM(), this);
    }

    public void processStatus(GatewayStatuses oldStatus, GatewayStatuses newStatus) {
        if(oldStatus.equals(GatewayStatuses.STARTING) && newStatus.equals(GatewayStatuses.STARTED)) {
            startInitialization();
        }
    }

    public void processUSSDResponse(String body) {
        long number = StringMethods.findLongNumber(body);
        simExecutor.create(number);
        status = SIM_DEFINED;
        LOG.trace("Gateway ID: {}. SIM successfully initialized. Status: {}.", ID, status);
    }

    public void processMessage(Message message) {
        message.setSim(simExecutor.getCurrentSIM());

        aggregatorRequestExecutor.matchMessageWithRequest(message);
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
        return ID;
    }

    public String getGatewayId() {
        return modemExecutor.getModem().getGatewayId();
    }

    private void startInitialization() {
        modemExecutor.sendGetNumberUSSD();
    }
}


