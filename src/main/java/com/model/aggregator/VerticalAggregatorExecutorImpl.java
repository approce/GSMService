package com.model.aggregator;

import com.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "VerticalAggregatorExecutorImpl")
public class VerticalAggregatorExecutorImpl extends AggregatorExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(VerticalAggregatorExecutorImpl.class);
    private VerticalAggregator verticalAggregator;

    @Override
    public void setUSSDMessage(String message) {

    }

    @Override
    public void setMessage(Message message) {

    }

    @Override
    public void setAggregator(Aggregator aggregator) {
        if (aggregator instanceof VerticalAggregator) {
            this.verticalAggregator = (VerticalAggregator) aggregator;
        } else {
            LOG.error("Wrong aggregator set to {}", this.getClass().getName());
        }
    }

    @Override
    @XmlElement
    public Aggregator getAggregator() {
        return verticalAggregator;
    }
}
