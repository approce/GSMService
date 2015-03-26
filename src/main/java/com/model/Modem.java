package com.model;

import org.smslib.AGateway;
import org.smslib.modem.SerialModemGateway;
import javax.persistence.*;

public class Modem {

    private Integer id;

    private String IMEI;

    private String port;

    private String brand;

    private String model;

    private Integer gatewaySpeed;

    private String initCommand;

    transient private SerialModemGateway serialModemGateway;

    transient private SIM sim;

    public void initGateway() {
        serialModemGateway = new SerialModemGateway("modem_" + id, port, gatewaySpeed, brand, model);
        serialModemGateway.setProtocol(AGateway.Protocols.PDU);
        serialModemGateway.setInbound(true);
        serialModemGateway.setCustomInitString(initCommand);
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
