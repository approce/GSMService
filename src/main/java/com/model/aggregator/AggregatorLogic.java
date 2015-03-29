package com.model.aggregator;

import com.model.Message;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class AggregatorLogic {

    public abstract void setUSSDMessage(String message);

    public abstract void setMessage(Message message);

    public abstract void setAggregator(Aggregator aggregator);

    public abstract Aggregator getAggregator();

}
