package sms.com.aggregators;

import org.ajwcc.pduUtils.gsm3040.PduUtils;
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

import static org.smslib.AGateway.GatewayStatuses;

public class VerticalAggregatorExecutorImpl extends AggregatorExecutor {

    private static final int TRIES_COUNT = 5;

    private SIMCell SIM_CELL;

    private int AVAILABLE_COUNT;

    private SIM currentSIM;

    private int sentATCommandTimes = 0;

    public VerticalAggregatorExecutorImpl(String id, Boolean startOnSetup, Modem modem,
                                          String simCell, int availableCount) {
        super(id, startOnSetup, modem);
        this.SIM_CELL = SIMCellFactory.getSIMCell(simCell);
        this.AVAILABLE_COUNT = availableCount;
    }

    @Override
    public double match(Request request) {
        //TODO add logic.
        return 10;
    }

    @Override
    public void processStatus(GatewayStatuses oldStatus, GatewayStatuses newStatus) {
        if(oldStatus.equals(GatewayStatuses.STARTING) &&
                newStatus.equals(GatewayStatuses.STARTED)) {
            startInitialization();
        }
    }

    @Override
    public void processUSSDResponse(USSDResponse response) {
        String body = null;
        try {
            body = getBody(response);
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
        }
    }

    private void setSIM(long number) {
        SIM sim = new SIM(number, SIM_CELL.SIM_PROVIDER);
        this.currentSIM = sim;


    }

    private void startInitialization() {
        String command =
                SMSLibUtils.convertGetNumberCommand(SIM_CELL.SIM_PROVIDER.GET_NUMBER_COMMAND);
        sendATCommand(command);
    }

    private void sendATCommand(String command) {
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

    private String getBody(USSDResponse ussdResponse) throws Exception {
        String bodyDecoded = StringMethods.find(ussdResponse.getRawResponse(), "\"", "\"");
        return PduUtils.decodeUcs2Encoding(null, PduUtils.pduToBytes(bodyDecoded));
    }
}
