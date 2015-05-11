package sms.com.matcher;

import sms.com.aggregators.AggregatorExecutor;
import sms.com.model.Request;

import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static sms.com.model.Request.RequestStatus.EXECUTING;

public class RequestMatcher {

    private Comparator<RequestMatch> comparator = new RequestMatchComparator();

    private LinkedList<RequestMatch> requestMatchList = new LinkedList<>();

    public Request setMatchedAggregator(List<AggregatorExecutor> aggregatorExecutorList, Request request) {

        prepareMatcherList(aggregatorExecutorList, request);

        requestMatchList.sort(comparator);

        RequestMatch mostAppropriateResult = requestMatchList.getFirst();

        boolean canBeExecuted = mostAppropriateResult.isExecutable();

        if(canBeExecuted) {
            setAggregator(request, mostAppropriateResult.getAggregatorExecutor());
        }
        return request;
    }

    private void prepareMatcherList(List<AggregatorExecutor> aggregatorExecutorList, Request request) {

        aggregatorExecutorList.forEach(aggregatorExecutor -> {
            RequestMatch result = aggregatorExecutor.match(request);
            requestMatchList.add(result);
        });
    }

    private void setAggregator(Request request, AggregatorExecutor aggregatorExecutor) {
        request.setAggregator_id(aggregatorExecutor.getId());
        request.setRequestStatus(EXECUTING);
        request.setStart_date(Calendar.getInstance());
    }
}