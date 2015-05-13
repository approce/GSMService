package sms.com.aggregators;

import sms.com.matcher.RequestMatch;
import sms.com.model.Request;
import sms.com.model.SIM;

public class AggregatorRequestMatcher {

    public RequestMatch match(Request request, SIM currentSim) {
        RequestMatch result = new RequestMatch();

        boolean present = currentSim.getMessageList().stream()
                                    .filter(m -> request.getOffer().equals(m.getOffer()))
                                    .findFirst()
                                    .isPresent();
        if(!present) {
            result.setCanBeExecuted(true);
        }
        return result;
    }
}