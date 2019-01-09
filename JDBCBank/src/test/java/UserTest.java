import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import banking.model.Account;
import banking.model.User;

public class UserTest {
	private final User u = new User();
	
	@Test
	public void UserIDTest() {
		u.setUserID(9);
		
		assertTrue(u.getUserID().equals(9));
	}
	
	@Test
	public void UserIDFailTest() {
		u.setUserID(10);
		assertFalse(u.getUserID().equals(9));
	}
	
	@Test
	public void UserFirstNameTest() {
		u.setFirstName("Leon");
		
		assertTrue(u.getFirstName().equals("Leon"));
	}
	
	@Test
	public void UserFirstNameFailTest() {
		u.setFirstName("Noel");
		
		assertFalse(u.getFirstName().equals("Leon"));
	}
	
	@Test
	public void UserLastNameTest() {
		u.setLastName("Wilson");
		
		assertTrue(u.getLastName().equals("Wilson"));
	}
	
	@Test
	public void UserLastNameFailTest() {
		u.setLastName("Nosliw");
		
		assertFalse(u.getLastName().equals("Wilson"));
	}
	
	@Test
	public void UsernameTest() {
		u.setUsername("lwilson");
		
		assertTrue(u.getUsername().equals("lwilson"));
	}
	
	@Test
	public void UsernameFailTest() {
		u.setUsername("lnosliwl");
		
		assertFalse(u.getUsername().equals("lwilson"));
	}
	
	@Test
	public void UserPhoneNumberTest() {
		u.setPhoneNumber("888-888-8888");
		
		assertTrue(u.getPhoneNumber().equals("888-888-8888"));
	}
	
	@Test
	public void UserPhoneNumberFailTest() {
		u.setPhoneNumber("999-999-9999");
		
		assertFalse(u.getPhoneNumber().equals("888-888-8888"));
	}
	
	@Test
	public void UserPasswordTest() {
		u.setPassword("password1234");
		
		assertTrue(u.getPassword().equals("password1234"));
	}
	
	@Test
	public void UserPasswordFailTest() {
		u.setPassword("password1111");
		
		assertFalse(u.getPassword().equals("password1234"));
	}
	
	@Test
	public void UserHasNoAccountsTest() {
		u.setAccounts(new HashSet<>());
		assertTrue(u.getAccounts().size() == 0);
	}
	
	@Test
	public void UserHasNoAccountsFailTest() {
		u.setAccounts(new HashSet<>());
		
		assertFalse(u.getAccounts().size() == 1);
	}
	
	@Test
	public void UserHasOneAccountTest() {
		u.setAccounts(new HashSet<>());
		
		u.addAccount(new Account());
		assertTrue(u.getAccounts().size() == 1);
	}
	
	@Test
	public void UserHasOneAccountFailTest() {
		u.setAccounts(new HashSet<>());
		
		u.addAccount(new Account());
		assertFalse(u.getAccounts().size() == 0);
	}
	
	@Test
	public void UserHasMultipleAccounts() {
		u.setAccounts(new HashSet<>());
		
		u.addAccount(new Account());
		u.addAccount(new Account());
		u.addAccount(new Account());
		assertTrue(u.getAccounts().size() == 3);
	}
	
	@Test
	public void AccessedAccountNotNull() {
		u.setAccessedAccount(new Account());
		
		assertTrue(u.getAccessedAccount() != null);
	}
	
}
