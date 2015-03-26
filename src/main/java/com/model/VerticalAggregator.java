package com.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Roman Zayats on 25.03.2015.
 */
@XmlRootElement(name = "aggregator")
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
