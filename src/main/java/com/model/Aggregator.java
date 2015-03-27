package com.model;

public abstract class Aggregator {

    private Integer id;

    private Modem modem;

    private String description;

    abstract void initialize();





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


}
