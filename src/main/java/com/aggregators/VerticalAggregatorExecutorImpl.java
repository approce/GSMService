package com.aggregators;

import com.model.Modem;
import com.model.SIMCell;
import com.model.SIMProvider;
import com.utils.SMSLibUtils;
import org.smslib.GatewayException;
import org.smslib.TimeoutException;

import java.io.IOException;

public class VerticalAggregatorExecutorImpl extends AggregatorExecutor {

    private SIMCell SIM_CELL;
    private int AVAILABLE_COUNT;


    public VerticalAggregatorExecutorImpl(String ID, Boolean START_ON_SETUP, Modem MODEM,
                                          String simCell, int AVAILABLE_COUNT) {
        super(ID, START_ON_SETUP, MODEM);
        this.SIM_CELL = new SIMCell(simCell, new SIMProvider("Beline","*161#"));
        this.AVAILABLE_COUNT = AVAILABLE_COUNT;
    }

    @Override
    public void sendGetNumberUSSD() {
        {
            String command = SMSLibUtils
                    .convertGetNumberCommand(SIM_CELL.SIM_PROVIDER.GET_NUMBER_COMMAND);
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
}
