package banking.model;

import java.util.HashSet;
import java.util.Set;

public class SuperUser {

	private Integer userID;
	private final String username;
	private String password;
	private Set<User> users;
	
//---CONSTRUCTORS---//
	
	//--NEW SUPER USER--//
	public SuperUser() {
		this.userID = 0000;
		this.username = "root";
		this.setPassword("Administration-Dec");
		this.setUsers(new HashSet<User>());
	}
	
	//--EXISTING SUPER USER--//
	public SuperUser(Integer userID, String username, String password, HashSet<User> users) {
		this.userID = userID;
		this.username = username;
		this.setPassword(password);
		this.setUsers(users);
	}
	
//---FUNCTIONS---//
	
	//--DISPLAY--//
	public void displayUser(User user) {
		
	}
	
	public void displayAllUser() {
		
	}
	
	//--FUNCTIONALITY--//
	public void addUser(User user) {
		
	}
	
	public void removeUser(User user) {
		
	}
	
	
	//MIGHT NEED A TON OF UPDATE METHODS (OH NOOOOOOOOOOOOOOOOO)
	public void updateUser(User user) {
		
	}
	
	public void deleteAll() {
		
	}
	

	//--GETTERS/SETTERS--//
	public Integer getUserID() {
		return userID;
	}
	
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
