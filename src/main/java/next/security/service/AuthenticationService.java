package next.security.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import next.tarjeta.entity.CreditCard;
import next.tarjeta.repository.CreditCardRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	CreditCardRepository creditCardRepository;

	
	@Override
	public UserDetails loadUserByUsername(String number) throws UsernameNotFoundException {
		

		Optional<CreditCard> creditCardOptional = creditCardRepository.findByNumber(number);
				
		if (!creditCardOptional.isPresent())
			throw new UsernameNotFoundException("Card " + number + " was not found");
		
		CreditCard creditCard = creditCardOptional.get();		
		return new User(creditCard.getNumber(), creditCard.getPassword(),Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
	}

		
}
