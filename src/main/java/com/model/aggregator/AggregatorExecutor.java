package com.model.aggregator;

import com.model.Message;
import com.model.Modem;

public abstract class AggregatorExecutor {

    public final String ID;
    public final Boolean START_ON_SETUP;
    public final Modem MODEM;

    public AggregatorExecutor(String ID, Boolean START_ON_SETUP, Modem MODEM) {
        this.ID = ID;
        this.START_ON_SETUP = START_ON_SETUP;
        this.MODEM = MODEM;
    }

    public abstract void setUSSDMessage(String message);

    public abstract void setMessage(Message message);

}
