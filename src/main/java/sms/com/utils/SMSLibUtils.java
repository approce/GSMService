package sms.com.utils;

import org.ajwcc.pduUtils.gsm3040.PduUtils;
import org.smslib.USSDResponse;

public class SMSLibUtils {

    public static String convertGetNumberCommand(String command) {
        byte[] dataToSend = null;
        byte[] dataToSend2 = null;
        dataToSend = PduUtils.stringToUnencodedSeptets(command);
        dataToSend2 = PduUtils.encode7bitUserData(null, dataToSend);
        command = PduUtils.bytesToPdu(dataToSend2);
        command = "AT+CUSD=1,\"" + command + "\",15\r";
        return command;
    }

    public static String convertUSSDToText(USSDResponse ussdResponse) throws Exception {
        String bodyDecoded = StringMethods.find(ussdResponse.getRawResponse(), "\"", "\"");
        return PduUtils.decodeUcs2Encoding(null, PduUtils.pduToBytes(bodyDecoded));
    }
}
