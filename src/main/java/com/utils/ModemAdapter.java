package com.utils;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Roman Zayats on 27.03.2015.
 */

public class ModemAdapter {

    private String id;

    private String IMEI;

    private String port;

    private Integer baudRate;

    private String initCommand;

    private String manufacturer;

    private String model;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    @XmlAttribute
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @XmlAttribute
    public Integer getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(Integer baudRate) {
        this.baudRate = baudRate;
    }

    @XmlAttribute
    public String getInitCommand() {
        return initCommand;
    }

    public void setInitCommand(String initCommand) {
        this.initCommand = initCommand;
    }

    @XmlAttribute
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @XmlAttribute
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
