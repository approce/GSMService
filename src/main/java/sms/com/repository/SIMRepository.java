package sms.com.repository;

import org.springframework.data.repository.CrudRepository;
import sms.com.model.SIM;

public interface SIMRepository extends CrudRepository<SIM, Long> {
}