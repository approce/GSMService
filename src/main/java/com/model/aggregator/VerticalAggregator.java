package com.model.aggregator;

import com.model.sim.SIMCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "aggregator")
public class VerticalAggregator extends Aggregator {
    private SIMCell simCell;
    private static final Logger LOG = LoggerFactory.getLogger(VerticalAggregator.class);

    @XmlElement
    public SIMCell getSimCell() {
        return simCell;
    }

    public void setSimCell(SIMCell simCell) {
        this.simCell = simCell;
    }


}
