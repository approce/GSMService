package sms.com.repository;

import org.springframework.data.repository.CrudRepository;
import sms.com.model.Request;

public interface RequestRepository extends CrudRepository<Request, Long> {
}