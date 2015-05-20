package sms.com.matcher;

import sms.com.aggregators.AbstractAggregatorFacade;
import sms.com.model.Request;

import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static sms.com.model.Request.RequestStatus.EXECUTING;

public class RequestMatcher {

    private Comparator<RequestMatch> comparator = new RequestMatchComparator();

    public AbstractAggregatorFacade setMatchedAggregator(List<AbstractAggregatorFacade> abstractAggregatorFacadeList,
                                                         Request request) {

        LinkedList<RequestMatch> requestMatchList = getMatcherAggregatorMatches(abstractAggregatorFacadeList, request);

        requestMatchList.sort(comparator);

        RequestMatch mostAppropriateResult = requestMatchList.getFirst();

        if(mostAppropriateResult.isExecutable()) {
            assignAggregatorToRequest(request, mostAppropriateResult.getAbstractAggregatorFacade());
            return mostAppropriateResult.getAbstractAggregatorFacade();
        } else {
            return null;
        }
    }

    private LinkedList<RequestMatch> getMatcherAggregatorMatches(List<AbstractAggregatorFacade> aggregatorList,
                                                                 Request request) {
        return aggregatorList.stream().map(aggregatorExecutor -> aggregatorExecutor.match(request))
                             .collect(Collectors.toCollection(LinkedList::new));
    }

    private void assignAggregatorToRequest(Request request, AbstractAggregatorFacade abstractAggregatorFacade) {
        request.setAggregator_id(abstractAggregatorFacade.getId());
        request.setRequestStatus(EXECUTING);
        request.setStart_date(Calendar.getInstance());
    }
}