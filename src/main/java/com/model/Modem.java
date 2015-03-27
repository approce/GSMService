package com.model;

import com.model.sim.SIM;
import com.utils.ModemAdapterConverter;
import org.smslib.GatewayException;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.IOException;

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
    public String getManufacturer() throws TimeoutException, GatewayException, IOException, InterruptedException {
//        return super.getManufacturer();
        return manufacturer;
    }

    @Override
    public String getModel() throws TimeoutException, GatewayException, IOException, InterruptedException {
//        return super.getModel();
        return model;
    }
}
