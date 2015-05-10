package sms.com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sms.com.model.SIMCell;

@Repository
public interface SIMCellRepository extends CrudRepository<SIMCell, String> {


}