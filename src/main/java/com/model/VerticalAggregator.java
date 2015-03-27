package com.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Roman Zayats on 25.03.2015.
 */
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

    @Override
    public void initialize() {
        LOG.debug("Start initialization vertical aggregator id:{} ", getId());
        //0. check if modem requires additional activities like AT command
        //1. check if sim card require additional activities like PIN code
        //2. try to start modem
    }
}
