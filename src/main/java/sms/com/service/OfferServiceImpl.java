package sms.com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.com.remote.model.Offer;
import sms.com.remote.repository.OfferRemoteRepository;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private static final Logger LOG = LoggerFactory.getLogger(OfferServiceImpl.class);

    @Autowired
    private OfferRemoteRepository offerRemoteRepository;

    private List<Offer> offerList = new LinkedList<>();

    @PostConstruct
    public void getServicesFromRemote() {
        long start = System.currentTimeMillis();
        offerRemoteRepository.findAll()
                                    .forEach(offerList::add);
        long duration = (System.currentTimeMillis() - start);

        LOG.debug("OfferServiceImpl have been successfully initialized. " +
                          "Downloaded from remote server {} Service models. Time spent: {} milliseconds",
                  offerList.size(), duration);
    }

    @Override
    public List<Offer> getServiceModels() {
        return offerList;
    }

    @Override
    public Offer getServiceModel(String shortName) throws IllegalArgumentException {
        for(Offer offer : offerList) {
            if(offer.getShort_name().equals(shortName)) {
                return offer;
            }
        }
        throw new IllegalArgumentException("Offer with short name " + shortName + "does not exist");
    }
}