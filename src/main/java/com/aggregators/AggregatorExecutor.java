package com.aggregators;

import com.model.Modem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AggregatorExecutor {

    protected static final Logger LOG = LoggerFactory.getLogger(AggregatorExecutor.class);
    public final String ID;
    public final Boolean START_ON_SETUP;
    public final Modem MODEM;

    public AggregatorExecutor(String id, Boolean startOnSetup, Modem modem) {
        this.ID = id;
        this.START_ON_SETUP = startOnSetup;
        this.MODEM = modem;
    }

    public abstract void sendGetNumberUSSD();

    public abstract void initialize();
}
