package com.model.aggregator;

import com.model.Message;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class AggregatorExecutor {

    private boolean startOnSetup;

    public abstract void setUSSDMessage(String message);

    public abstract void setMessage(Message message);

    public abstract void setAggregator(Aggregator aggregator);

    public abstract Aggregator getAggregator();

    @XmlAttribute
    public boolean isStartOnSetup() {
        return startOnSetup;
    }

    public void setStartOnSetup(boolean startOnSetup) {
        this.startOnSetup = startOnSetup;
    }
}
