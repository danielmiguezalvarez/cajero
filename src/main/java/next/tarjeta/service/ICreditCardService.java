package next.tarjeta.service;

import next.tarjeta.dto.CreditCardDto;

public interface ICreditCardService {
	public void registerCreditCard(CreditCardDto creditCard) throws Exception;
}
