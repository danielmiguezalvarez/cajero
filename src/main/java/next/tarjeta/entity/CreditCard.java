package next.tarjeta.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import next.cuenta.entity.Account;

@Entity
@Table(name = "credit_card")
@EntityListeners(CreditCardListener.class)
public class CreditCard {	

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @NotBlank(message = "Number is mandatory")
    @Column(name="number", unique=true)
	private String number;
    
    private String password;
    
	private String type;
	
    @OneToOne
    @JoinColumn(name = "id")
    private Account account;  
    
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
