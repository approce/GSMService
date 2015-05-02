package com.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.model.Message;
import com.service.interfaces.MessageService;
import config.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Context.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;

    private static final String MESSAGE_AGGREGATOR_ID = "aggregator123";
    private static final String MESSAGE_ORIGINATOR = "Popular brand";
    private static final Date MESSAGE_DATE = new Date(1427629088000l);
    private static final String MESSAGE_TEXT = "I am message test text";

    @Test
    @DatabaseSetup(value = "/dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void saveTest() throws Exception {
        Message message = new Message();
        message.setAggregator_id(MESSAGE_AGGREGATOR_ID);
        message.setOriginator(MESSAGE_ORIGINATOR);
        Calendar date = Calendar.getInstance();
        date.setTime(MESSAGE_DATE);
        message.setDate(date);
        message.setText(MESSAGE_TEXT);

        messageService.save(message);

        Message receivedMessage = null;

        assertNotNull(receivedMessage);
        assertEquals(receivedMessage.getAggregator_id(), MESSAGE_AGGREGATOR_ID);
        assertEquals(receivedMessage.getOriginator(), MESSAGE_ORIGINATOR);
        assertEquals(receivedMessage.getDate().getTimeInMillis(), MESSAGE_DATE.getTime());
        assertEquals(receivedMessage.getText(), MESSAGE_TEXT);
    }
}