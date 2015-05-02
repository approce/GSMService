package com.model;

public class SIM {

    public final Integer ID;

    public final Integer NUMBER;

    public final SIMProvider SIM_PROVIDER;

    public SIM(Integer ID, Integer NUMBER, SIMProvider SIM_PROVIDER) {
        this.ID = ID;
        this.NUMBER = NUMBER;
        this.SIM_PROVIDER = SIM_PROVIDER;
    }
}
