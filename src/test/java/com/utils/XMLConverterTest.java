package com.utils;

import com.model.Aggregator;
import com.model.HorizontalAggregator;
import com.model.Modem;
import com.model.SIMCell;
import com.model.VerticalAggregator;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class XMLConverterTest {

    private static XMLConverter<Aggregator> converter;
    private static final String MODEM_IMEI = "1365372637";
    private static final String SIM_CELL = "A4";
    private static final String DESCRIPTION = "Short description about aggregator if need";
    private static final String XML_PATH = "test_xml_converter.xml";

    @Test
    public void testConverter() throws Exception {
        converter = new XMLConverter<>(VerticalAggregator.class, HorizontalAggregator.class, XMLListWrapper.class);

        List<Aggregator> values = new LinkedList<>();
        VerticalAggregator verticalAggregator = new VerticalAggregator();
        Modem modem = new Modem();
        modem.setId(1);
        modem.setIMEI(MODEM_IMEI);
        verticalAggregator.setModem(modem);
        SIMCell simCell = new SIMCell();
        simCell.setName(SIM_CELL);
        verticalAggregator.setSimCell(simCell);
        verticalAggregator.setId(1);
        verticalAggregator.setDescription(DESCRIPTION);
        values.add(verticalAggregator);
        HorizontalAggregator horizontalAggregator = new HorizontalAggregator();
        horizontalAggregator.setId(2);
        values.add(horizontalAggregator);
        converter.doMarshall(values, XML_PATH);

        List<Aggregator> read_values = converter.doUnmarshall(XML_PATH);
        assertThat(read_values.get(0), instanceOf(VerticalAggregator.class));
        assertEquals(read_values.get(0).getId(), new Integer(1));
        assertEquals(((VerticalAggregator) read_values.get(0)).getSimCell().getName(), SIM_CELL);
        assertEquals(read_values.get(0).getDescription(), DESCRIPTION);
        assertEquals(read_values.get(0).getModem().getId(),new Integer(1));
        assertEquals(read_values.get(0).getModem().getIMEI(), MODEM_IMEI);
        assertThat(read_values.get(1), instanceOf(HorizontalAggregator.class));
        assertEquals(read_values.get(1).getId(), new Integer(2));

        new File(XML_PATH).delete();
    }
}