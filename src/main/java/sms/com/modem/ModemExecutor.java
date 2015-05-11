package sms.com.modem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.GatewayException;
import org.smslib.TimeoutException;
import sms.com.model.Modem;
import sms.com.utils.SMSLibUtils;

import java.io.IOException;

public class ModemExecutor {

    protected static final Logger LOG = LoggerFactory.getLogger(ModemExecutor.class);

    private final String ID;

    private final Modem modem;

    private final String getNumberUSSDCommand;

    public ModemExecutor(String id, Modem modem, String getNumberUSSD) {
        this.ID = id;
        this.modem = modem;
        this.getNumberUSSDCommand = SMSLibUtils.convertGetNumberCommand(getNumberUSSD);
    }

    public Modem getModem() {
        return modem;
    }

    public void sendGetNumberUSSD() {
        try {
            this.modem.sendCustomATCommand(getNumberUSSDCommand);
            LOG.debug("Gateway id: {}. Successfully USSD requested to get number.",
                      ID);
        } catch(GatewayException | TimeoutException | IOException | InterruptedException e) {
            LOG.error("Gateway id: {}. Error while send USSD request to get number.\n{}",
                      ID, e);
        }
    }
}