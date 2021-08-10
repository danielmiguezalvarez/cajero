package next.crediccard.tarjeta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import next.tarjeta.entity.CreditCard;
import next.tarjeta.repository.CreditCardRepository;

@SpringBootTest
public class TrajetaRepositoryTest {

    
    @Autowired
    private CreditCardRepository creditCardRepository;
    
    @Test
    public void whenFindingCreditCardById_thenCorrect() {
    	
    	var creditCard = new CreditCard();
    	
    	creditCard.setNumber("3602-1411-789456123");
    	creditCard.setPassword("1234");
    	creditCard.setType("CREDITO");
    	
        CreditCard c = creditCardRepository.save(creditCard);
        Optional<CreditCard> c2 = creditCardRepository.findById(c.getId());
        assertEquals(c2.get(),c);
    }
    
    @Test
    public void whenFindingAllCreditCard_thenCorrect() {
    	CreditCard c1 = new CreditCard();
    	CreditCard c2 = new CreditCard();
    	
    	c1.setNumber("5555-1411-789456123");
    	c1.setPassword("1234");
    	c1.setType("CREDITO");

    	c2.setNumber("3333-1411-789456123");
    	c2.setPassword("4321");
    	c2.setType("DEBITO");    	
    	
    	creditCardRepository.save(c1);
    	creditCardRepository.save(c2);
    	assertThat(creditCardRepository.findAll(), hasItem(c1));
    	assertThat(creditCardRepository.findAll(), hasItem(c2));
    }
    
    
    @Test
    public void whenFindByNumber_thenCorrect() {
    	
    	var creditCard = new CreditCard();    	
    	creditCard.setNumber("6666-1411-789456123");
    	creditCard.setPassword("1234");
    	creditCard.setType("CREDITO");

    	
    	CreditCard c1 = creditCardRepository.save(creditCard);
    	
        Optional<CreditCard> c2 = creditCardRepository.findByNumber(c1.getNumber());
        assertEquals(c2.get(),c1);
    }

}
