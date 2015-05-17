package sms.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sms.com.model.Offer;
import sms.com.model.Request;
import sms.com.repository.OfferRepository;
import sms.com.service.RequestPoolService;

@RestController
public class Controller {

    @Autowired
    private RequestPoolService requestPoolService;

    @Autowired
    private OfferRepository offerRepository;

    @RequestMapping("/")
    String createRequest(@RequestParam String offer) {
        Request request = new Request();
        Offer requestOffer = offerRepository.findOne(offer);
        request.setOffer(requestOffer);

        requestPoolService.add(request);

        return "OK";
    }
}
