package com.model;

import com.model.sim.SIM;
import org.smslib.AGateway;
import org.smslib.modem.SerialModemGateway;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getGatewaySpeed() {
        return gatewaySpeed;
    }

    public void setGatewaySpeed(Integer gatewaySpeed) {
        this.gatewaySpeed = gatewaySpeed;
    }

    public String getInitCommand() {
        return initCommand;
    }

    public void setInitCommand(String initCommand) {
        this.initCommand = initCommand;
    }

    public SerialModemGateway getSerialModemGateway() {
        return serialModemGateway;
    }

    public void setSerialModemGateway(SerialModemGateway serialModemGateway) {
        this.serialModemGateway = serialModemGateway;
    }

    public SIM getSim() {
        return sim;
    }

    public void setSim(SIM sim) {
        this.sim = sim;
    }
}
