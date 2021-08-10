package next.tarjeta.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import next.tarjeta.entity.CreditCard;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard,UUID>{

	public CreditCard findByName(@Param("name")String name);

}
