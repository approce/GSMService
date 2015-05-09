package sms.com.aggregators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.GatewayException;
import org.smslib.TimeoutException;
import org.smslib.USSDResponse;
import sms.com.model.Modem;
import sms.com.model.Request;
import sms.com.model.SIM;
import sms.com.model.SIMCell;
import sms.com.model.SIMCellFactory;
import sms.com.utils.SMSLibUtils;
import sms.com.utils.StringMethods;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public abstract class AggregatorExecutor {

    public static enum AggregatorStatus {
        EXECUTING, FAILED_TO_START, NUMBER_ASSIGNED
    }

    protected static final Logger LOG = LoggerFactory.getLogger(AggregatorExecutor.class);

    protected static final int TRIES_COUNT = 5;

    public final String ID;

    public final Boolean START_ON_SETUP;

    public final Modem MODEM;

    protected final SIMCell simCell;

    protected final String initializationATCommand;

    public AggregatorStatus status;

    protected int sentATCommandTimes = 0;

    protected SIM currentSIM;

    private final Set<Request> requests = new HashSet<>();

    private final SimFactory simFactory;

    public AggregatorExecutor(String id, Boolean startOnSetup, Modem modem, String simCell) {
        this.ID = id;
        this.START_ON_SETUP = startOnSetup;
        this.MODEM = modem;
        this.simCell = SIMCellFactory.getSIMCell(simCell);
        this.simFactory = new SimFactory(this.simCell.SIM_PROVIDER);
        this.initializationATCommand =
                SMSLibUtils.convertGetNumberCommand(this.simCell.SIM_PROVIDER.GET_NUMBER_COMMAND);
    }

    public abstract double match(Request request);

    public void processStatus(GatewayStatuses oldStatus, GatewayStatuses newStatus) {
        if(oldStatus.equals(GatewayStatuses.STARTING) &&
                newStatus.equals(GatewayStatuses.STARTED)) {
            startInitialization();
        }
    }

    public void startInitialization() {
        sendATCommand(initializationATCommand);
    }

    public void processUSSDResponse(USSDResponse response) {
        String body = null;
        try {
            body = SMSLibUtils.convertUSSDToText(response);
        } catch(Exception e) {
            LOG.error("Gateway id: {} Exception while converting USSD response to String" +
                              ".\nBody:{}\n{}", MODEM.ID, response.getRawResponse(), e);
            if(sentATCommandTimes < TRIES_COUNT) {
                startInitialization();
            } else {
                status = AggregatorStatus.FAILED_TO_START;
            }
        }
        if(body != null) {
            long number = StringMethods.findLongNumber(body);
            setSIM(number);
        }
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    protected void sendATCommand(String command) {
        try {
            MODEM.sendCustomATCommand(command);
            LOG.debug("Gateway id: {}. Successfully USSD requested to get number.",
                      MODEM.getGatewayId());
        } catch(GatewayException | TimeoutException | IOException | InterruptedException e) {
            LOG.error("Gateway id: {}. Error while send USSD request to get number.\n{}",
                      MODEM.getGatewayId(), e);
        }
        sentATCommandTimes++;
    }

    private void setSIM(long number) {
        currentSIM = simFactory.createSIM(number);
        status = AggregatorStatus.NUMBER_ASSIGNED;
    }
}
