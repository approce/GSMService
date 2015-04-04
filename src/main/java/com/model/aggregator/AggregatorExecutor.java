package com.model.aggregator;

import com.model.Message;
import com.model.Modem;

public abstract class AggregatorExecutor {

    private String aggregatorId;
    private Boolean startOnSetup;
    private Modem modem;

    public abstract void setUSSDMessage(String message);

    public abstract void setMessage(Message message);

    public String getAggregatorId() {
        return aggregatorId;
    }

    public void setAggregatorId(String aggregatorId) {
        this.aggregatorId = aggregatorId;
    }

    public Boolean isStartOnSetup() {
        return startOnSetup;
    }

    public void setStartOnSetup(Boolean startOnSetup) {
        this.startOnSetup = startOnSetup;
    }

    public Modem getModem() {
        return modem;
    }

    public void setModem(Modem modem) {
        this.modem = modem;
    }
}
