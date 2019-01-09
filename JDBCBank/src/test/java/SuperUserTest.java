import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import banking.model.SuperUser;
import banking.model.User;

public class SuperUserTest {
	private final SuperUser su = new SuperUser();
	@Test
	public void SuperUserIDTest() {
		su.setUserID(0);
		
		assertTrue(su.getUserID().equals(0));
	}
	
	@Test
	public void SuperUserIDFailTest() {
		su.setUserID(1);
		
		assertFalse(su.getUserID().equals(0));
	}
	
	@Test
	public void SuperUsernameTest() {
		su.setUsername("root1");
		
		assertTrue(su.getUsername().equals("root1"));
	}
	
	@Test
	public void SuperUsernameFailTest() {
		su.setUsername("root2");
		assertFalse(su.getUsername().equals("root1"));
	}
	
	@Test
	public void SuperUserPasswordTest() {
		su.setPassword("password1234");
		
		assertTrue(su.getPassword().equals("password1234"));
	}
	
	@Test
	public void SuperUserPasswordFailTest() {
		su.setPassword("password1111");
		assertFalse(su.getPassword().equals("password1234"));
	}
	
	@Test
	public void SuperUserHasNoUsersTest() {
		su.setUsers(new ArrayList<User>());
		
		assertTrue(su.getUsers().size() == 0);
	}
	
	@Test
	public void SuperUserHasNoUsersFailTest() {
		su.setUsers(new ArrayList<User>());
		
		su.getUsers().add(new User());
		
		assertFalse(su.getUsers().size() == 0);
	}
	
	@Test
	public void UserHasOneUserTest() {
		su.setUsers(new ArrayList<User>());
		su.getUsers().add(new User());
		
		assertTrue(su.getUsers().size() == 1);
	}
	
	@Test
	public void UserHasOneUserFailTest() {
		su.setUsers(new ArrayList<User>());
		
		assertFalse(su.getUsers().size() == 1);
	}
	
	@Test
	public void UserHasMultipleUsers() {
		su.setUsers(new ArrayList<User>());
		su.getUsers().add(new User());
		su.getUsers().add(new User());
		su.getUsers().add(new User());
		
		assertTrue(su.getUsers().size() == 3);
	}
	
	
}
