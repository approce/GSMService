package sms.com.aggregators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway.GatewayStatuses;
import org.springframework.stereotype.Component;
import sms.com.matcher.RequestMatch;
import sms.com.model.Message;
import sms.com.model.Modem;
import sms.com.model.Request;
import sms.com.model.SIM;
import sms.com.modem.ModemExecutor;
import sms.com.utils.StringMethods;

import java.util.HashSet;
import java.util.Set;

import static sms.com.aggregators.AggregatorStatus.SIM_DEFINED;

@Component
public abstract class AggregatorExecutor {

    protected static final Logger LOG = LoggerFactory.getLogger(AggregatorExecutor.class);

    private final String ID;

    private final Boolean startOnSetup;

    private final Set<Request> requests = new HashSet<>();

    private final ModemExecutor modemExecutor;

    private final SIMExecutor simExecutor;

    private final AggregatorRequestMatcher aggregatorRequestMatcher;

    public AggregatorStatus status;

    public AggregatorExecutor(String id,
                              Boolean startOnSetup,
                              ModemExecutor modemExecutor,
                              SIMExecutor simExecutor,
                              AggregatorRequestMatcher aggregatorRequestMatcher) {
        this.ID = id;
        this.startOnSetup = startOnSetup;
        this.modemExecutor = modemExecutor;
        this.simExecutor = simExecutor;
        this.aggregatorRequestMatcher = aggregatorRequestMatcher;
    }

    public RequestMatch match(Request request, SIM currentSim) {
        RequestMatch result = aggregatorRequestMatcher.match(request, simExecutor.getCurrentSIM());
        result.setAggregatorExecutor(this);
        return result;
    }

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
        simExecutor.create(number);
        status = SIM_DEFINED;
    }

    public void processMessage(Message message) {

    }

    public void addRequest(Request request) {
        requests.add(request);
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
}


