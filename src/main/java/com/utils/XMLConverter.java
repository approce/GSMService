package com.utils;

import com.model.*;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Roman Zayats on 24.03.2015.
 */
@Component
public class XMLConverter<T> {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public XMLConverter(Class... classesToBeBound) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(classesToBeBound);
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        unmarshaller = jaxbContext.createUnmarshaller();
    }

    public void doMarshall(List<T> values, String path) throws JAXBException {
        XMLListWrapper<T> xmlListWrapper = new XMLListWrapper<T>();
        xmlListWrapper.setList(values);
        marshaller.marshal(xmlListWrapper, new File(path));
    }

    public List<T> doUnmarshall(String path) throws JAXBException {
        XMLListWrapper<T> xmlListWrapper = (XMLListWrapper<T>) unmarshaller.unmarshal(new File(path));
        return xmlListWrapper.getList();
    }

    public static void main(String[] args) throws Exception {
        XMLConverter<Aggregator> xmlConverter = new XMLConverter<>(VerticalAggregator.class, HorizontalAggregator.class, XMLListWrapper.class);
        List<Aggregator> values = new LinkedList<>();
        VerticalAggregator verticalAggregator = new VerticalAggregator();
        verticalAggregator.setSimCell(new SIMCell());
        verticalAggregator.setId(12);
        Modem modem = new Modem();
        modem.setId(123);
        modem.setIMEI("aadsadasd");
        verticalAggregator.setModem(modem);
        values.add(verticalAggregator);
        values.add(new HorizontalAggregator());
        xmlConverter.doMarshall(values, "5.xml");

        List<Aggregator> values_input = new LinkedList<>();
        values_input = xmlConverter.doUnmarshall("5.xml");
        for (Aggregator ag : values_input) {
            if (ag instanceof VerticalAggregator) {
                System.out.println("vertical");
            }
            if (ag instanceof HorizontalAggregator) {
                System.out.println("horizontal");
            }
        }
    }
}
