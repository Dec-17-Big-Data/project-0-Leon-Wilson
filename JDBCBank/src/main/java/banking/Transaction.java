package banking;

public class Transaction {
	final String recipient;
	final Double amount;
	final TransactionType type;
	
//---CONSTRUCTORS---//
	
	/***
	 * @author Leon Wilson
	 */
	public Transaction(String recipient, Double amount, TransactionType type) {
		this.recipient = recipient;
		this.amount = amount;
		this.type = type;
	}
	
//---FUNCTIONS---//
	
	/***
	 * @author Leon Wilson
	 */
	public void approveTransaction(Account account) {
		if(type == TransactionType.debit) {
			
		}
	}
	
	//---GETTERS/SETTERS---//
}
