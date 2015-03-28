package com.model.aggregator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "aggregator")
public class HorizontalAggregator extends Aggregator {
    private static final Logger LOG = LoggerFactory.getLogger(HorizontalAggregator.class);
}
