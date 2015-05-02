package com.aggregators;

import com.model.Modem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AggregatorExecutor {

    protected static final Logger LOG = LoggerFactory.getLogger(AggregatorExecutor.class);
    public final String ID;
    public final Boolean START_ON_SETUP;
    public final Modem MODEM;

    public AggregatorExecutor(String ID, Boolean START_ON_SETUP, Modem MODEM) {
        this.ID = ID;
        this.START_ON_SETUP = START_ON_SETUP;
        this.MODEM = MODEM;
    }

    public abstract void sendGetNumberUSSD();
}
