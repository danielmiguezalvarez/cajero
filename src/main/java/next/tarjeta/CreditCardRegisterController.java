package next.tarjeta;

import java.lang.reflect.MalformedParametersException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import next.tarjeta.dto.CreditCardDto;
import next.tarjeta.exceptions.RepeatedCreditCard;
import next.tarjeta.exceptions.ValidatePasswordExceptions;
import next.tarjeta.service.ICreditCardService;

@RestController
@RequestMapping("")
public class CreditCardRegisterController {

	@Autowired
	ICreditCardService creditCardService;
	
	@PostMapping("/creditCard")
	public ResponseEntity<String> addCreditCard(@RequestBody CreditCardDto creditCard) {
		try {

			creditCardService.registerCreditCard(creditCard);
			return ResponseEntity.ok("Registration completed");
			
		} catch (RepeatedCreditCard e) {
			return new ResponseEntity<>("Credit card already exists", HttpStatus.CONFLICT);
		} catch (MalformedParametersException e) {
			return new ResponseEntity<>("Malformed expected data", HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (ValidatePasswordExceptions ex1) {
			return new ResponseEntity<>(ex1.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception ex) {
			return new ResponseEntity<>("Unexpected API error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
		
}
