package com.model;

import org.smslib.AGateway;
import org.smslib.modem.SerialModemGateway;

public class Modem extends SerialModemGateway {

    public final String ID;

    public final String IMEI;

    public final String port;

    public final Integer baudRate;

    public final String initCommand;

    public final String manufacturer;

    public final String model;

    public SIM sim;

    public Modem(String id, String IMEI, String comPort, int baudRate,
                 String initCommand, String manufacturer, String model) {
        super(id, comPort, baudRate, manufacturer, model);
        this.ID=id;
        this.IMEI = IMEI;
        this.port = comPort;
        this.baudRate = baudRate;
        this.initCommand = initCommand;
        this.manufacturer = manufacturer;
        this.model = model;
        this.setProtocol(AGateway.Protocols.PDU);
        this.setInbound(true);
        this.setCustomInitString(initCommand);
    }
}