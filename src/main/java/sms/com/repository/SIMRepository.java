package sms.com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sms.com.model.SIM;

@Repository
public interface SIMRepository extends CrudRepository<SIM, Long> {
}