package com.utils.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "configuration")
public class XMLListWrapper<T> {

    private List<T> list;

    @XmlElementWrapper(name = "elements_list")
    @XmlElement(name = "element")
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
