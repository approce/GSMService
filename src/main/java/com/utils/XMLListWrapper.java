package com.utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Roman Zayats on 26.03.2015.
 */
@XmlRootElement
public class XMLListWrapper<T> {

    private List<T> list;

    @XmlElementWrapper(name = "element")
    @XmlElement
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
