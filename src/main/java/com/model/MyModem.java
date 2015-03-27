package com.model;

import com.model.sim.SIM;
import com.utils.xml.ModemAdapterConverter;
import org.smslib.message.MsIsdn;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(ModemAdapterConverter.class)
public class MyModem extends org.smslib.gateway.modem.Modem {

    private final String IMEI;

    private final String port;

    private final Integer baudRate;

    private final String initCommand;

    private final String manufacturer;

    private final String model;

    transient private SIM sim;

    public MyModem(String IMEI, String initCommand, String id, String comPort, int baudRate, String manufacturer, String model) {
        super(id, comPort, baudRate, "", "", new MsIsdn(""), "");
        this.IMEI = IMEI;
        this.port = comPort;
        this.baudRate = baudRate;
        this.initCommand = initCommand;
        this.manufacturer = manufacturer;
        this.model = model;
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

    public String getManufacturer() {
//        return super.getManufacturer();
        return manufacturer;
    }

    public String getModel() {
//        return super.getModel();
        return model;
    }
}
