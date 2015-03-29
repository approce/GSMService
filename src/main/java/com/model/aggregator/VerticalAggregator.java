package com.model.aggregator;

import com.model.sim.SIMCell;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "VerticalAggregator")
public class VerticalAggregator extends Aggregator {
    private SIMCell simCell;

    @XmlElement
    public SIMCell getSimCell() {
        return simCell;
    }

    public void setSimCell(SIMCell simCell) {
        this.simCell = simCell;
    }


}
