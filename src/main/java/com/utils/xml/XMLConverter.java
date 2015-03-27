package com.utils.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * Created by Roman Zayats on 24.03.2015.
 */
public class XMLConverter<T> {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    private static final Logger LOG = LoggerFactory.getLogger(XMLConverter.class);

    public XMLConverter(Class... classesToBeBound) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classesToBeBound);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            LOG.error("Exception during XMLConverter initialization {}\n{}", classesToBeBound, e);
        }
    }

    public void doMarshall(List<T> values, String path) {
        try {
            XMLListWrapper<T> xmlListWrapper = new XMLListWrapper<T>();
            xmlListWrapper.setList(values);
            marshaller.marshal(xmlListWrapper, new File(path));
        } catch (JAXBException e) {
            LOG.error("Exception during XMLConverter marshalling\n{}", e);
        }
    }

    public List<T> doUnmarshall(String path) {
        try {
            XMLListWrapper<T> xmlListWrapper = (XMLListWrapper<T>) unmarshaller.unmarshal(new File(path));
            return xmlListWrapper.getList();
        } catch (JAXBException e) {
            LOG.error("Exception during XMLConverter unmarshalling\n{}", e);
        }
        return null;
    }
}
