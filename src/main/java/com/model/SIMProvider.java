package com.model;

public class SIMProvider {

    //TODO use hibernate
    public final String NAME;

    public final String GET_NUMBER_COMMAND;

    public SIMProvider(String NAME, String GET_NUMBER_COMMAND) {
        this.NAME = NAME;
        this.GET_NUMBER_COMMAND = GET_NUMBER_COMMAND;
    }

}
