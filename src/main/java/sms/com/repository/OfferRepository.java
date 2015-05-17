package sms.com.repository;

import org.springframework.data.repository.CrudRepository;
import sms.com.model.Offer;

public interface OfferRepository extends CrudRepository<Offer, String> {
}