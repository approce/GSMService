package com.model;

import com.model.sim.SIM;
import com.utils.xml.ModemAdapterConverter;
import org.smslib.AGateway;
import org.smslib.modem.SerialModemGateway;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(ModemAdapterConverter.class)
public class Modem extends SerialModemGateway {

    private final String IMEI;

    private final String port;

    private final Integer baudRate;

    private final String initCommand;

    private final String manufacturer;

    private final String model;

    transient private SIM sim;

    public Modem(String IMEI, String initCommand, String id, String comPort, int baudRate, String manufacturer, String model) {
        super(id, comPort, baudRate, manufacturer, model);
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

    public String getId() {
        return getGatewayId();
    }

    public String getIMEI() {
        return IMEI;
    }

    public String getPort() {
        return port;
    }

    public Integer getBaudRate() {
        return baudRate;
    }

    public String getInitCommand() {
        return initCommand;
    }

    @Override
    public String getManufacturer() {
//        return super.getManufacturer();
        return manufacturer;
    }

    @Override
    public String getModel() {
//        return super.getModel();
        return model;
    }
}
