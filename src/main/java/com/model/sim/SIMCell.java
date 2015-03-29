package com.model.sim;

import javax.xml.bind.annotation.XmlAttribute;

public class SIMCell {

    private String name;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
