package sms.com.aggregators;

import org.smslib.GatewayException;
import org.smslib.TimeoutException;
import sms.com.model.Modem;
import sms.com.model.Request;
import sms.com.model.SIM;
import sms.com.model.SIMCell;
import sms.com.model.SIMCellFactory;
import sms.com.utils.SMSLibUtils;

import java.io.IOException;

public class VerticalAggregatorExecutorImpl extends AggregatorExecutor {

    private SIMCell SIM_CELL;

    private int AVAILABLE_COUNT;

    private SIM currentSIM;

    public VerticalAggregatorExecutorImpl(String id, Boolean startOnSetup, Modem modem,
                                          String simCell, int availableCount) {
        super(id, startOnSetup, modem);
        this.SIM_CELL = SIMCellFactory.getSIMCell(simCell);
        this.AVAILABLE_COUNT = availableCount;
    }

    @Override
    public void sendGetNumberUSSD() {
        String command =
                SMSLibUtils.convertGetNumberCommand(SIM_CELL.SIM_PROVIDER.GET_NUMBER_COMMAND);
        sendATCommand(command);
    }

    @Override
    public void initialize() {

    }

    @Override
    public double match(Request request) {
        //TODO add logic.
        return 10;
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
    }
}
