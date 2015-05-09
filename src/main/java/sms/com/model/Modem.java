package sms.com.model;

import org.smslib.modem.SerialModemGateway;

public class Modem extends SerialModemGateway {

    public final String ID;

    public final String IMEI;

    public final String port;

    public final String initCommand;

    public Modem(String id, String IMEI, String comPort, int baudRate,
                 String initCommand, String manufacturer, String model) {
        super(id, comPort, baudRate, manufacturer, model);
        this.ID = id;
        this.IMEI = IMEI;
        this.port = comPort;
        this.initCommand = initCommand;
        initParameters(initCommand);
    }

    private void initParameters(String initCommand) {
        this.setProtocol(Protocols.PDU);
        this.setInbound(true);
        this.setCustomInitString(initCommand);
    }
}