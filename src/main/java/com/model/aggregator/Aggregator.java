package com.model.aggregator;

import com.model.Modem;

import javax.xml.bind.annotation.XmlAttribute;

public abstract class Aggregator {

    private String id;

    private Modem modem;

    private String description;

    private boolean startOnSetup;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Modem getModem() {
        return modem;
    }

    public void setModem(Modem modem) {
        this.modem = modem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlAttribute
    public boolean isStartOnSetup() {
        return startOnSetup;
    }

    public void setStartOnSetup(boolean startOnSetup) {
        this.startOnSetup = startOnSetup;
    }
}
