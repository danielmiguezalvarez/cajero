package next.cuenta.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import next.tarjeta.entity.CreditCard;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
    @NotBlank(message = "account number is mandatory")
    @Column(name="account_number", unique=true)
	private String accountNumber;
    
    @Column(name="balance")
    private Double balance;
    
    @OneToOne(mappedBy = "account")
    private CreditCard creditCard;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditcard) {
		this.creditCard = creditcard;
	}
    
    
}
