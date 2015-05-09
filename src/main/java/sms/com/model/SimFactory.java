package sms.com.model;

public class SimFactory {

    private SIMProvider provider;

    public SimFactory(SIMProvider provider) {
        this.provider = provider;
    }

    public SIM createSIM(long number) {
        return new SIM(number, provider);
    }
}