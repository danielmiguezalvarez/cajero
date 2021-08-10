package next.cuenta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import next.cuenta.entity.Account;
import next.cuenta.repository.AccountRepository;
import next.cuenta.service.IAcountService;

@Service
public class AccountService implements IAcountService{

	private static Integer accountNumberGenerater = 0;
	
	@Autowired
	AccountRepository accountRepository;
	@Override
	public Account genetateNewAccount() {
		
		var account = new Account();
		
		account.setAccountNumber(accountNumberGenerater.toString());
		accountNumberGenerater++;
		account.setBalance(0.0);
		
		return accountRepository.save(account);
		
	}

}
