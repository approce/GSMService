package sms.com.matcher;

import sms.com.aggregators.AggregatorFacade;
import sms.com.model.Request;

import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static sms.com.model.Request.RequestStatus.EXECUTING;

public class RequestMatcher {

    private Comparator<RequestMatch> comparator = new RequestMatchComparator();

    public AggregatorFacade setMatchedAggregator(List<AggregatorFacade> aggregatorFacadeList, Request request) {

        LinkedList<RequestMatch> requestMatchList = getMatcherAggregatorMatches(aggregatorFacadeList, request);

        requestMatchList.sort(comparator);

        RequestMatch mostAppropriateResult = requestMatchList.getFirst();

        boolean canBeExecuted = mostAppropriateResult.isExecutable();

        if(canBeExecuted) {
            assignAggregatorToRequest(request, mostAppropriateResult.getAggregatorFacade());
        }
        return mostAppropriateResult.getAggregatorFacade();
    }

    private LinkedList<RequestMatch> getMatcherAggregatorMatches(
            List<AggregatorFacade> aggregatorFacadeList, Request request) {
        return aggregatorFacadeList.stream()
                                     .map(aggregatorExecutor -> aggregatorExecutor.match(request))
                                     .collect(Collectors.toCollection(LinkedList::new));
    }

    private void assignAggregatorToRequest(Request request, AggregatorFacade aggregatorFacade) {
        request.setAggregator_id(aggregatorFacade.getId());
        request.setRequestStatus(EXECUTING);
        request.setStart_date(Calendar.getInstance());
    }
}