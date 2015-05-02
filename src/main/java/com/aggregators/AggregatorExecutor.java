package com.aggregators;

import com.model.Modem;
import com.utils.SMSLibUtils;
import org.smslib.GatewayException;
import org.smslib.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class AggregatorExecutor {

    public final String ID;
    public final Boolean START_ON_SETUP;
    public final Modem MODEM;

    private static final Logger LOG = LoggerFactory.getLogger(AggregatorExecutor.class);

    public AggregatorExecutor(String ID, Boolean START_ON_SETUP, Modem MODEM) {
        this.ID = ID;
        this.START_ON_SETUP = START_ON_SETUP;
        this.MODEM = MODEM;
    }

    public void sendGetNumberUSSD() {
        String command = SMSLibUtils
                .convertGetNumberCommand(MODEM.sim.SIM_PROVIDER.GET_NUMBER_COMMAND);
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
