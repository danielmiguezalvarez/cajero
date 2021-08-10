package next.tarjeta.service.impl;

import java.lang.reflect.MalformedParametersException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import next.tarjeta.dto.CreditCardDto;
import next.tarjeta.entity.CreditCard;
import next.tarjeta.exceptions.RepeatedCreditCard;
import next.tarjeta.exceptions.ValidatePasswordExceptions;
import next.tarjeta.repository.CreditCardRepository;
import next.tarjeta.service.ICreditCardService;

@Service
public class CreditCardService implements ICreditCardService {

	private static final String DEBITO = "DEBITO";
	private static final String CREDITO = "CREDITO";
	@Autowired
	CreditCardRepository creditCardRepository;

	@Override
	public void registerCreditCard(CreditCardDto creditCard) throws Exception {

		validateCreditCard(creditCard);
		validatePassword(creditCard);
		saveCreditCard(creditCard);
	}


	private void validateCreditCard(CreditCardDto creditCard) throws RepeatedCreditCard {

		if (creditCard.getNumber() == null || creditCard.getNumber() == null || creditCard.getNumber().equals("")
				|| creditCard.getPassword() == null || creditCard.getType() == null || invalidType(creditCard))
			throw new MalformedParametersException();

		if (existCreditCard(creditCard.getNumber()))
			throw new RepeatedCreditCard("Credit card already exists");

	}


	private boolean invalidType(CreditCardDto creditCard) {
		return !creditCard.equals(CREDITO) && creditCard.equals(DEBITO);
	}
	
	private void validatePassword(CreditCardDto creditCard) throws Exception {
		PasswordValidator validator = new PasswordValidator(Arrays.asList(new LengthRule(4, 4),
				new CharacterRule(EnglishCharacterData.Digit, 4),
				new WhitespaceRule()));

		RuleResult result = validator.validate(new PasswordData(creditCard.getPassword()));

		if (!result.isValid()) {
			List<String> messages = validator.getMessages(result);
			String messageTemplate = messages.stream().collect(Collectors.joining(","));

			throw new ValidatePasswordExceptions(messageTemplate);
		}
	}


	private Boolean existCreditCard(String number) {
		var safebox = creditCardRepository.findByNumber(number);
		return safebox != null;
	}

	private CreditCard saveCreditCard(CreditCardDto creditCard) {		
		return creditCardRepository.save(getCreditCardFromDto(creditCard));
	}


	private CreditCard getCreditCardFromDto(CreditCardDto creditCardDto) {
		var creditCard = new CreditCard();
		creditCard.setNumber(creditCardDto.getNumber());
		creditCard.setPassword(creditCardDto.getPassword());
		creditCard.setType(creditCardDto.getType());
		return creditCard;
	}


}
