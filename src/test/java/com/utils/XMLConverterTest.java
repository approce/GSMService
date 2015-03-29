package com.utils;

import com.model.Modem;
import com.model.aggregator.AggregatorExecutor;
import com.model.aggregator.VerticalAggregator;
import com.model.aggregator.VerticalAggregatorExecutorImpl;
import com.model.sim.SIMCell;
import com.utils.xml.XMLConverter;
import com.utils.xml.XMLListWrapper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class XMLConverterTest {

    private static final String MODEM_ID = "1430a";
    private static final String MODEM_IMEI = "1365372637";
    private static final String MODEM_INIT_COMMAND = "AT+CFUN";
    private static final String MODEM_PORT = "COM-14";
    private static final Integer MODEM_BAUD_RATE = 54700;
    private static final String MODEM_MANUFACTURER = "HUAWEI";
    private static final String MODEM_MODEL = "E1550";

    private static final String AGGREGATOR_ID = "13";
    private static final String SIM_CELL = "A4";
    private static final String XML_PATH = "test_xml_converter.xml";

    private static VerticalAggregatorExecutorImpl verticalAggregatorLogic;

    @Before
    public void initialize() {
        Modem modem = new Modem(MODEM_IMEI, MODEM_INIT_COMMAND,
                MODEM_ID, MODEM_PORT, MODEM_BAUD_RATE, MODEM_MANUFACTURER, MODEM_MODEL);

        VerticalAggregator verticalAggregator = new VerticalAggregator();
        verticalAggregator.setId(AGGREGATOR_ID);
        verticalAggregator.setModem(modem);
        SIMCell simCell = new SIMCell();
        simCell.setName(SIM_CELL);
        verticalAggregator.setSimCell(simCell);

        verticalAggregatorLogic = new VerticalAggregatorExecutorImpl();
        verticalAggregatorLogic.setStartOnSetup(true);
        verticalAggregatorLogic.setAggregator(verticalAggregator);
    }

    @Test
    public void aggregatorLogicListTest() throws Exception {
        XMLConverter<AggregatorExecutor> xmlConverter =
                new XMLConverter<AggregatorExecutor>(XMLListWrapper.class, VerticalAggregatorExecutorImpl.class, VerticalAggregator.class);
        LinkedList<AggregatorExecutor> aggregatorExecutors = new LinkedList<>();
        aggregatorExecutors.add(verticalAggregatorLogic);
        xmlConverter.doMarshall(aggregatorExecutors, XML_PATH);

        List<AggregatorExecutor> aggregatorLogics1 = xmlConverter.doUnmarshall(XML_PATH);

        assertThat(aggregatorLogics1.get(0), instanceOf(VerticalAggregatorExecutorImpl.class));
        VerticalAggregatorExecutorImpl verticalAggregatorLogic = (VerticalAggregatorExecutorImpl) aggregatorLogics1.get(0);

        assertThat(verticalAggregatorLogic.getAggregator(), instanceOf(VerticalAggregator.class));
        assertTrue(verticalAggregatorLogic.isStartOnSetup());
        VerticalAggregator verticalAggregator = (VerticalAggregator) verticalAggregatorLogic.getAggregator();
        assertEquals(verticalAggregator.getId(), AGGREGATOR_ID);
        assertEquals(verticalAggregator.getSimCell().getName(), SIM_CELL);

        Modem modem = verticalAggregator.getModem();
        assertEquals(modem.getId(), MODEM_ID);
        assertEquals(modem.getIMEI(), MODEM_IMEI);
        assertEquals(modem.getInitCommand(), MODEM_INIT_COMMAND);
        assertEquals(modem.getPort(), MODEM_PORT);
        assertEquals(modem.getBaudRate(), MODEM_BAUD_RATE);
        assertEquals(modem.getManufacturer(), MODEM_MANUFACTURER);
        assertEquals(modem.getModel(), MODEM_MODEL);

        new File(XML_PATH).delete();
    }
}