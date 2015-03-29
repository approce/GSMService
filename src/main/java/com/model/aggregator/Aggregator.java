package com.model.aggregator;

import com.model.Modem;


public abstract class Aggregator {

    private String id;

    private Modem modem;


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


}
