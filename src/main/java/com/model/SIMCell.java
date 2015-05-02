package com.model;

public class SIMCell {

    //TODO: use hibernate
    public final String NAME;

    public final SIMProvider SIM_PROVIDER;

    public SIMCell(String NAME, SIMProvider SIM_PROVIDER) {
        this.NAME = NAME;
        this.SIM_PROVIDER = SIM_PROVIDER;
    }
}
