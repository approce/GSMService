package sms.com.remote.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sms.com.remote.model.Offer;

@Repository
public interface OfferRemoteRepository extends CrudRepository<Offer, Long> {
}