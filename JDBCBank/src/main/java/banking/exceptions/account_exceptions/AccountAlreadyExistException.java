package banking.exceptions.account_exceptions;

public class AccountAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3759104710180330915L;

	public AccountAlreadyExistException(String m) {
		super(m);
	}
}
