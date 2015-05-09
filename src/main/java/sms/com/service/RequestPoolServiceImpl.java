package sms.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.aggregators.AggregatorExecutor;
import sms.com.model.Request;
import sms.com.repository.RequestRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RequestPoolServiceImpl implements RequestPoolService {

    private static final Set<Request> AVAILABLE_REQUEST_SET = new HashSet<>();

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AggregatorService aggregatorService;

    public static Set<Request> getAvailableRequestSet() {
        return AVAILABLE_REQUEST_SET;
    }

    @Override
    public void add(Request request) {
        AVAILABLE_REQUEST_SET.add(request);
        saveToDB(request);
        matchRequestToAggregators(request);
    }

    private void matchRequestToAggregators(Request request) {
        List<AggregatorExecutor> aggregators = aggregatorService.getAggregators();
        double currentMatchIndex = 0;
        AggregatorExecutor currentAggregator = null;

        for(AggregatorExecutor aggregator : aggregators) {
            double matchIndex = aggregator.match(request);

            if(matchIndex > currentMatchIndex) {
                currentAggregator = aggregator;
                currentMatchIndex = matchIndex;
            }
        }

        if(currentAggregator != null) {
            request.setRequestStatus(Request.RequestStatus.EXECUTING);
            currentAggregator.addRequest(request);
            AVAILABLE_REQUEST_SET.remove(request);
            saveToDB(request);
        }
    }

    private void saveToDB(Request request) {
        requestRepository.save(request);
    }
}