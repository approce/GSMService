package sms.com.repository;

import org.springframework.data.repository.CrudRepository;
import sms.com.model.SIMProvider;

public interface ProviderRepository extends CrudRepository<SIMProvider, Integer> {
}