package sms.com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.aggregators.AbstractAggregatorFacade;
import sms.com.controller.RemoteController;
import sms.com.matcher.RequestMatcher;
import sms.com.model.Request;
import sms.com.repository.RequestRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class RequestPoolServiceImpl implements RequestPoolService {

    private static final Logger LOG = LoggerFactory.getLogger(RequestPoolServiceImpl.class);

    private static final List<Request> AVAILABLE_REQUEST_LIST = new LinkedList<>();

    @Autowired
    private RequestMatcher requestMatcher;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AggregatorPoolService aggregatorPoolService;

    @Autowired
    private RemoteController remoteController;

    public static List<Request> getAvailableRequestSet() {
        return AVAILABLE_REQUEST_LIST;
    }

    @Override
    public void add(Request request) {
        LOG.trace("Request received: {}", request);
        saveToDB(request);
        AVAILABLE_REQUEST_LIST.add(request);
        LOG.trace("Available request list[{}]: {}", AVAILABLE_REQUEST_LIST.size(), AVAILABLE_REQUEST_LIST);
        matchRequestToAggregators(request);
    }

    @Override
    public void finish(Request request) {
        remoteController.sendRequestToRemote(request);
    }

    private void matchRequestToAggregators(Request request) {
        List<AbstractAggregatorFacade> availableAggregators = aggregatorPoolService.getAggregators();
        AbstractAggregatorFacade matchedAggregator = requestMatcher.setMatchedAggregator(availableAggregators, request);

        if(matchedAggregator != null) {
            matchedAggregator.addRequest(request);
            AVAILABLE_REQUEST_LIST.remove(request);
            saveToDB(request);
            LOG.trace("Request {} have been matched to aggregator", request);
            LOG.trace("Available request list[{}]: {}", AVAILABLE_REQUEST_LIST.size(), AVAILABLE_REQUEST_LIST);
        }
    }

    private void saveToDB(Request request) {
        requestRepository.save(request);
    }
}