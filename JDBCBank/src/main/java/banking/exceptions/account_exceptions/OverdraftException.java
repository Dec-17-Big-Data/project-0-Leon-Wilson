package banking.exceptions.account_exceptions;

public class OverdraftException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3212853670973803575L;

	public OverdraftException(String m) {
		super(m);
	}
}
