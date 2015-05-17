package sms.com.repository;

import org.springframework.data.repository.CrudRepository;
import sms.com.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
}