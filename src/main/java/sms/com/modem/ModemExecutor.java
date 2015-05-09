package sms.com.modem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.GatewayException;
import org.smslib.TimeoutException;
import sms.com.model.Modem;

import java.io.IOException;

public class ModemExecutor {

    protected static final Logger LOG = LoggerFactory.getLogger(ModemExecutor.class);

    private Modem modem;

    private String getNumberUSSD;

    public ModemExecutor(Modem modem) {
        this.modem = modem;
    }

    public void setGetNumberUSSDCommand(String initializationATCommand) {
        this.getNumberUSSD = initializationATCommand;
    }

    public Modem getModem() {
        return modem;
    }

    public void sendGetNumberUSSD() {
        try {
            this.modem.sendCustomATCommand(getNumberUSSD);
            LOG.debug("Gateway id: {}. Successfully USSD requested to get number.",
                      modem.getGatewayId());
        } catch(GatewayException | TimeoutException | IOException | InterruptedException e) {
            LOG.error("Gateway id: {}. Error while send USSD request to get number.\n{}",
                      modem.getGatewayId(), e);
        }
    }
}