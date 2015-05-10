package sms.com.service;


import sms.com.remote.model.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> getServiceModels();

    Offer getServiceModel(String shortName) throws IllegalArgumentException;

}