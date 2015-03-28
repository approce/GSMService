package com.utils;

import com.model.Modem;
import com.model.aggregator.Aggregator;
import com.model.aggregator.HorizontalAggregator;
import com.model.aggregator.VerticalAggregator;
import com.model.sim.SIMCell;
import com.utils.xml.XMLConverter;
import com.utils.xml.XMLListWrapper;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class XMLConverterTest {

    private static XMLConverter<Aggregator> converter;
    private static final String MODEM_ID = "1430a";
    private static final String MODEM_IMEI = "1365372637";
    private static final String MODEM_INIT_COMMAND = "AT+CFUN";
    private static final String MODEM_PORT = "COM-14";
    private static final Integer MODEM_BAUD_RATE = 54700;
    private static final String MODEM_MANUFACTURER = "HUAWEI";
    private static final String MODEM_MODEL = "E1550";

    private static final String SIM_CELL = "A4";
    private static final String DESCRIPTION = "Short description about aggregator if need";
    private static final String XML_PATH = "test_xml_converter.xml";

    @Test
    public void testConverter() throws Exception {
        converter = new XMLConverter<Aggregator>(VerticalAggregator.class, HorizontalAggregator.class, XMLListWrapper.class);
        List<Aggregator> values = new LinkedList<Aggregator>();
        VerticalAggregator verticalAggregator = new VerticalAggregator();
        Modem modem = new Modem(MODEM_IMEI, MODEM_INIT_COMMAND,
                MODEM_ID, MODEM_PORT, MODEM_BAUD_RATE, MODEM_MANUFACTURER, MODEM_MODEL);
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

        Modem verticalAggregatorModem = read_values.get(0).getModem();
        assertEquals(verticalAggregatorModem.getId(), MODEM_ID);
        assertEquals(verticalAggregatorModem.getIMEI(), MODEM_IMEI);
        assertEquals(verticalAggregatorModem.getPort(), MODEM_PORT);
        assertEquals(verticalAggregatorModem.getInitCommand(), MODEM_INIT_COMMAND);
        assertEquals(verticalAggregatorModem.getManufacturer(), MODEM_MANUFACTURER);
        assertEquals(verticalAggregatorModem.getModel(), MODEM_MODEL);
        assertEquals(verticalAggregatorModem.getBaudRate(), MODEM_BAUD_RATE);

        assertThat(read_values.get(1), instanceOf(HorizontalAggregator.class));
        assertEquals(read_values.get(1).getId(), new Integer(2));

        new File(XML_PATH).delete();
    }
}