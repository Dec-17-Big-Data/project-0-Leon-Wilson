import static org.junit.Assert.assertTrue;

import org.junit.Test;

import banking.model.User;

public class UserTest {
	
	
	@Test
	public void UserIDTest() {
		
	}
	
	@Test
	public void UserIDFailTest() {
		
	}
	
	@Test
	public void UserFirstNameTest() {
		User u = new User();
		u.setFirstName("Leon");
		
		assertTrue(u.getFirstName().equals("Leon"));
	}
	
	@Test
	public void UserFirstNameFailTest() {
		User u = new User();
		u.setFirstName("Leon");
		
		assertTrue(u.getFirstName().equals("Leon"));
	}
	
	@Test
	public void UserLastNameTest() {
		User u = new User();
		u.setFirstName("Leon");
		
		assertTrue(u.getFirstName().equals("Leon"));
	}
	
	@Test
	public void UserLastNameFailTest() {
		User u = new User();
		u.setFirstName("Leon");
		
		assertTrue(u.getFirstName().equals("Leon"));
	}
	
	@Test
	public void UsernameTest() {
		
	}
	
	@Test
	public void UsernameFailTest() {
		
	}
	
	/*
	private Set<Account> accounts;
	private Set<ChargeCard> cards;*/
	
	@Test
	public void UserPhoneNumberTest() {
		
	}
	
	@Test
	public void UserPhoneNumberFailTest() {
		
	}
	
	@Test
	public void UserPasswordTest() {
		
	}
	
	@Test
	public void UserPasswordFailTest() {
		
	}
	
	@Test
	public void UserHasNoAccounts() {
		
	}
	
	@Test
	public void UserHasOneAccounts() {
		
	}
	
	@Test
	public void UserHasMultipleAccounts() {
		
	}
	
	@Test
	public void AccountDeletedTest() {
		
	}
	
	@Test
	public void AccessedAccountNotNull() {
		
	}
	
}
