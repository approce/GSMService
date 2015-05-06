package com.aggregators;

import com.model.Modem;
import com.model.SIMCell;
import com.model.SIMCellFactory;
import com.utils.SMSLibUtils;
import org.smslib.GatewayException;
import org.smslib.TimeoutException;

import java.io.IOException;

public class VerticalAggregatorExecutorImpl extends AggregatorExecutor {

    private SIMCell SIM_CELL;
    private int AVAILABLE_COUNT;

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

    private void sendATCommand(String command) {
        try {
            MODEM.sendCustomATCommand(command);
            LOG.debug("Gateway id: {}. Successfully USSD requested to get number.",
                    MODEM.getGatewayId());
        } catch (GatewayException | TimeoutException | IOException | InterruptedException e) {
            LOG.error("Gateway id: {}. Error while send USSD request to get number.\n{}",
                    MODEM.getGatewayId(), e);
        }
    }
}
