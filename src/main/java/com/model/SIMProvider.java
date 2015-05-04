package com.model;

public class SIMProvider {

    public final Integer ID;

    //TODO use hibernate
    public final String NAME;

    public final String GET_NUMBER_COMMAND;

    public final String DESCRIPTION;

    public SIMProvider(Integer ID, String NAME, String GET_NUMBER_COMMAND, String DESCRIPTION) {
        this.ID = ID;
        this.NAME = NAME;
        this.GET_NUMBER_COMMAND = GET_NUMBER_COMMAND;
        this.DESCRIPTION = DESCRIPTION;
    }

}
