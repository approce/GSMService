package sms.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.aggregators.AggregatorFacade;
import sms.com.model.Message;
import sms.com.repository.MessageRepository;
import sms.com.repository.OfferRepository;
import sms.com.utils.StringMethods;

import java.util.stream.StreamSupport;

@Service(value = "MessageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private AggregatorPoolService aggregatorPoolService;

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void process(Message message) {
        matchOffer(message);
        AggregatorFacade aggregator = aggregatorPoolService.getAggregatorByGateway(message.getAggregator_id());
        aggregator.processMessage(message);

        messageRepository.save(message);
    }

    private void matchOffer(Message message) {
        StreamSupport.stream(offerRepository.findAll().spliterator(), false)
                     .filter(offer -> offer.getOriginator().equals(message.getOriginator())).findFirst()
                     .ifPresent(offer1 -> {
                         message.setOffer(offer1);
                         findCodeInMessage(message);
                     });

    }

    private void findCodeInMessage(Message message) {
        //TODO:add support of multiple strategies.
        long longNumber = StringMethods.findLongNumber(message.getBody());
        message.setCode(String.valueOf(longNumber));
    }
}
