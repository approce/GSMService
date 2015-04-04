package com.model.aggregator;

import com.model.Message;

public abstract class AggregatorExecutor {

    private boolean startOnSetup;

    public abstract void setUSSDMessage(String message);

    public abstract void setMessage(Message message);

    public boolean isStartOnSetup() {
        return startOnSetup;
    }

    public void setStartOnSetup(boolean startOnSetup) {
        this.startOnSetup = startOnSetup;
    }
}
