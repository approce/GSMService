package sms.com.aggregators;

import sms.com.model.SIM;
import sms.com.model.SIMProvider;

public class SimFactory {

    private SIMProvider provider;

    public SimFactory(SIMProvider provider) {
        this.provider = provider;
    }

    public SIM createSIM(long number) {
        return new SIM(number, provider);
    }
}