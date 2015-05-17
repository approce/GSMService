package sms.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.aggregators.AggregatorExecutor;
import sms.com.matcher.RequestMatcher;
import sms.com.model.Request;
import sms.com.repository.RequestRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static sms.com.model.Request.RequestStatus.EXECUTING;

@Service
public class RequestPoolServiceImpl implements RequestPoolService {

    private static final Set<Request> AVAILABLE_REQUEST_SET = new HashSet<>();

    private RequestMatcher requestMatcher = new RequestMatcher();

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AggregatorPoolService aggregatorPoolService;

    public static Set<Request> getAvailableRequestSet() {
        return AVAILABLE_REQUEST_SET;
    }

    @Override
    public void add(Request request) {
        saveToDB(request);
        AVAILABLE_REQUEST_SET.add(request);
        matchRequestToAggregators(request);
    }

    @Override
    public void finish(Request request) {
        // TODO: add implementation

    }

    private void matchRequestToAggregators(Request request) {
        List<AggregatorExecutor> availableAggregators = aggregatorPoolService.getAggregators();
        AggregatorExecutor aggregatorExecutor = requestMatcher.setMatchedAggregator(availableAggregators, request);

        boolean requestMatchedToAggregator = request.getRequestStatus().equals(EXECUTING);

        if(requestMatchedToAggregator) {
            aggregatorExecutor.addRequest(request);
            AVAILABLE_REQUEST_SET.remove(request);
            saveToDB(request);
        }
    }

    private void saveToDB(Request request) {
        requestRepository.save(request);
    }
}