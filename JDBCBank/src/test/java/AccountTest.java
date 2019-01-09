import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import banking.model.Account;
import banking.model.AccountTypes;

public class AccountTest {
/*
private String accountName;
private Integer accountID;
private Integer userID;
private final AccountTypes type;
private Double balance;
 * */
	private static final Account a = new Account();
	@Test
	public void AccountNameTest() {
		a.setAccountName("Account One");
		
		assertTrue(a.getAccountName().equals("Account One"));
	}
	
	@Test
	public void AccountNameFailTest() {
		a.setAccountName("Account Two");
		
		assertFalse(a.getAccountName().equals("Account One"));
	}
	
	@Test
	public void AccountUserIDTest() {
		a.setUserID(0);
		
		assertTrue(a.getUserID()== 0);
	}
	
	@Test
	public void AccountUserIDFailTest() {
		a.setUserID(1);
		
		assertFalse(a.getUserID()== 0);
	}
	
	@Test
	public void AccountIDTest() {
		a.setAccountID(0);
		
		assertTrue(a.getAccountID() == 0);
	}
	
	@Test
	public void AccountIDFailTest() {
		a.setAccountID(1);
		
		assertFalse(a.getAccountID() == 0);
	}
	
	@Test
	public void AccountTypeCheckingTest() {
		a.setType(AccountTypes.checking);
		
		assertTrue(a.getType().equals(AccountTypes.checking));
	}
	
	@Test
	public void AccountTypeSavingsTest() {
		a.setType(AccountTypes.savings);
		
		assertTrue(a.getType().equals(AccountTypes.savings));	
	}
	
	@Test
	public void AccountTypeFailTest() {
		a.setType(AccountTypes.checking);
		
		assertFalse(a.getType().equals(AccountTypes.savings));
	}
	
	@Test
	public void AccountBalanceTest() {
		a.setBalance(10.54);
		
		assertTrue(a.getBalance() == 10.54);
	}
	
	@Test
	public void AccountBalanceFailedTest() {
		a.setBalance(10.00);
		
		assertFalse(a.getBalance( )== 10.54);
	}
}
