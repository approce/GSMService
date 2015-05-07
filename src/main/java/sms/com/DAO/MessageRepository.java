package sms.com.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sms.com.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}