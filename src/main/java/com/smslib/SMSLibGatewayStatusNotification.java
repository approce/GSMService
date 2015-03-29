package com.smslib;

import com.model.Modem;
import org.ajwcc.pduUtils.gsm3040.PduUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.IGatewayStatusNotification;
import org.smslib.TimeoutException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SMSLibGatewayStatusNotification implements IGatewayStatusNotification {

    private static final Logger LOG = LoggerFactory.getLogger(SMSLibGatewayStatusNotification.class);

    @Override
    public void process(AGateway aGateway, AGateway.GatewayStatuses oldStatus, AGateway.GatewayStatuses newStatus) {
        LOG.debug("Gateway id:" + aGateway.getGatewayId() + " status " + oldStatus + " changed to " + newStatus);
        if (newStatus.equals(AGateway.GatewayStatuses.STARTED)) {
            String command = "*161*";
            byte[] dataToSend = null;
            byte[] dataToSend2 = null;
            dataToSend = PduUtils.stringToUnencodedSeptets(command);
            dataToSend2 = PduUtils.encode7bitUserData(null, dataToSend);
            command = PduUtils.bytesToPdu(dataToSend2);
            //send command to gateway from SMSlib:
            try {
                String resp = ((Modem)aGateway).sendCustomATCommand("AT+CUSD=1,\"" + command + "\",15\r");
            } catch (GatewayException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
