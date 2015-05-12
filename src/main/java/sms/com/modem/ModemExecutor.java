package sms.com.modem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.GatewayException;
import org.smslib.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import sms.com.aggregators.SIMExecutor;
import sms.com.model.Modem;
import sms.com.utils.SMSLibUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class ModemExecutor {

    protected static final Logger LOG = LoggerFactory.getLogger(ModemExecutor.class);

    private final String ID;

    private final Modem modem;

    @Autowired
    private SIMExecutor simExecutor;

    private String getNumberUSSDCommand;

    public ModemExecutor(String id, Modem modem) {
        this.ID = id;
        this.modem = modem;
    }

    @PostConstruct
    public void init() {
        String getNumberCode = simExecutor.getGetNumberCode();
        this.getNumberUSSDCommand = SMSLibUtils.convertGetNumberCommand(getNumberCode);
        LOG.trace("ModemExecutor ID: {} have been successfully initialized.", ID);
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