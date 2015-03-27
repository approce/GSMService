package com.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Roman Zayats on 25.03.2015.
 */
@XmlRootElement(name = "aggregator")
public class HorizontalAggregator extends Aggregator {

    private static final Logger LOG = LoggerFactory.getLogger(HorizontalAggregator.class);

    @Override
    public void initialize() {
        LOG.debug("Start initialization vertical aggregator id:{} ", getId());
        System.out.println("do initialization");
    }
}
