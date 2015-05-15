package sms.com.matcher;

import sms.com.aggregators.AggregatorExecutor;
import sms.com.model.Request;

import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static sms.com.model.Request.RequestStatus.EXECUTING;

public class RequestMatcher {

    private Comparator<RequestMatch> comparator = new RequestMatchComparator();

    public Request setMatchedAggregator(List<AggregatorExecutor> aggregatorExecutorList, Request request) {

        LinkedList<RequestMatch> requestMatchList = getMatcherAggregatorMatches(aggregatorExecutorList, request);

        requestMatchList.sort(comparator);

        RequestMatch mostAppropriateResult = requestMatchList.getFirst();

        boolean canBeExecuted = mostAppropriateResult.isExecutable();

        if(canBeExecuted) {
            assignAggregatorToRequest(request, mostAppropriateResult.getAggregatorExecutor());
        }
        return request;
    }

    private LinkedList<RequestMatch> getMatcherAggregatorMatches(
            List<AggregatorExecutor> aggregatorExecutorList, Request request) {
        return aggregatorExecutorList.stream()
                                     .map(aggregatorExecutor -> aggregatorExecutor.match(request))
                                     .collect(Collectors.toCollection(LinkedList::new));
    }

    private void assignAggregatorToRequest(Request request, AggregatorExecutor aggregatorExecutor) {
        request.setAggregator_id(aggregatorExecutor.getId());
        request.setRequestStatus(EXECUTING);
        request.setStart_date(Calendar.getInstance());
    }
}