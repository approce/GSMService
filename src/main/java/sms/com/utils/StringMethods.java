package sms.com.utils;

public class StringMethods {

    public static String find(String source, String patternBegin, String patternEnd)
            throws Exception {
        int startkey = source.indexOf(patternBegin);
        int stopkey;
        if(startkey > -1) {
            if(patternEnd.equals("endbody")) {
                stopkey = source.length();
            } else {
                stopkey = source.indexOf(patternEnd,
                                         startkey + patternBegin.length());
            }
            if(stopkey > -1) {
                String key = source.substring(startkey + patternBegin.length(),
                                              stopkey);
                return key;
            }
        }
        throw new Exception();
    }

    public static long findLongNumber(String message) {
        String big = "";
        String buffer = "";
        for(char c : message.toCharArray()) {
            if(Character.isDigit(c)) {
                buffer += c;
            } else {
                if(buffer.length() > big.length()) {
                    big = buffer;
                }
                buffer = "";
            }
        }
        if(buffer.length() > big.length()) {
            big = buffer;
        }
        return Long.parseLong(big);
    }
}
