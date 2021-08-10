package next.tarjeta.exceptions;


public class RepeatedCreditCard extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2195230597739741632L;
	
	public RepeatedCreditCard(String message) {
		super(message);
	}

}
