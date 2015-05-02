package com.model;

public class SIMProvider {

    public final Integer ID;

    public final String NAME;

    public final String GET_NUMBER_COMMAND;

    public SIMProvider(Integer ID, String NAME, String GET_NUMBER_COMMAND) {
        this.ID = ID;
        this.NAME = NAME;
        this.GET_NUMBER_COMMAND = GET_NUMBER_COMMAND;
    }

}
