package next.cuenta.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import next.cuenta.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {

}
