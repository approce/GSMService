package sms.com.model;

import org.smslib.modem.SerialModemGateway;

public class Modem extends SerialModemGateway {

    public final String IMEI;

    public final String PORT;

    public Modem(String id, String IMEI, String comPort, int baudRate, String initCommand,
                 String manufacturer, String model) {
        super(id, comPort, baudRate, manufacturer, model);
        this.IMEI = IMEI;
        this.PORT = comPort;
        initParameters(initCommand);
    }

    private void initParameters(String initCommand) {
        this.setProtocol(Protocols.PDU);
        this.setInbound(true);
        this.setCustomInitString(initCommand);
    }
}