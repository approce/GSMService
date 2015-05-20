package sms.com.aggregators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sms.com.matcher.RequestMatch;
import sms.com.model.Message;
import sms.com.model.Offer;
import sms.com.model.Request;
import sms.com.model.SIM;
import sms.com.service.RequestPoolService;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class AggregatorRequestExecutor {

    protected static final Logger LOG = LoggerFactory.getLogger(AggregatorRequestExecutor.class);

    private String ID;

    @Autowired
    private RequestPoolService requestPoolService;

    private Set<Request> requests = new ConcurrentSkipListSet<>();

    public AggregatorRequestExecutor(String id) {
        this.ID = id;
    }

    public void addRequest(Request request) {
        LOG.trace("Gateway ID: {}. Adding request: {}. ", ID, request);
        requests.add(request);
        LOG.trace("Gateway ID: {}. Request set: ", requests);
    }

    public RequestMatch matchRequest(Request request, SIM currentSim,
                                     AbstractAggregatorFacade abstractAggregatorFacade) {
        RequestMatch result = new RequestMatch();

        boolean present =
                currentSim.getMessageList().stream().filter(m -> request.getOffer().equals(m.getOffer())).findFirst()
                          .isPresent();
        if(!present) {
            result.setCanBeExecuted(true);
            result.setAbstractAggregatorFacade(abstractAggregatorFacade);
        }
        return result;
    }

    public void matchMessageWithRequest(Message message) {
        if(message.getOffer() == null) {
            return;
        }
        Offer messageOffer = message.getOffer();

        requests.stream().filter(request -> request.getOffer().equals(messageOffer)).forEach(request -> {
            request.addMessage(message);
            finishRequest(request);
        });
    }

    public void finishRequest(Request request) {
        LOG.trace("Gateway ID: {}. Finishing request: {}.", ID, request);
        requests.remove(request);
        requestPoolService.finish(request);
        LOG.trace("Gateway ID: {}. Request set: ", requests);
    }
}