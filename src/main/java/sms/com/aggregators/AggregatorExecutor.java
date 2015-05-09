package sms.com.aggregators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sms.com.model.Modem;
import sms.com.model.Request;

import java.util.HashSet;
import java.util.Set;

public abstract class AggregatorExecutor {

    protected static final Logger LOG = LoggerFactory.getLogger(AggregatorExecutor.class);
    public final String ID;
    public final Boolean START_ON_SETUP;
    public final Modem MODEM;
    public final Set<Request> requests = new HashSet<>();


    public AggregatorExecutor(String id, Boolean startOnSetup, Modem modem) {
        this.ID = id;
        this.START_ON_SETUP = startOnSetup;
        this.MODEM = modem;
    }

    public abstract void sendGetNumberUSSD();

    public abstract void initialize();

    public abstract double match(Request request);

    public void addRequest(Request request) {
        requests.add(request);
    }
}
