package com.model;

public class SIMCell {

    public final String ID;

    public final SIMProvider SIM_PROVIDER;

    public SIMCell(String ID, SIMProvider SIM_PROVIDER) {
        this.ID = ID;
        this.SIM_PROVIDER = SIM_PROVIDER;
    }
}
