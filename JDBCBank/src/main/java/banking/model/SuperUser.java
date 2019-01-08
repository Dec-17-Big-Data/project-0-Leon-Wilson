package banking.model;

import java.util.ArrayList;
import java.util.List;

public class SuperUser {

	private Integer userID;
	private String username;
	private String password;
	private List<User> users;
	
//---CONSTRUCTORS---//
	
	//--NEW SUPER USER--//
	public SuperUser() {
		this.userID = 0000;
		this.username = "root";
		this.setPassword("Administration-Dec");
		this.setUsers(new ArrayList<User>());
	}
	
	//--EXISTING SUPER USER--//
	public SuperUser(Integer userID, String username, String password, ArrayList<User> users) {
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
		for(User u : users) {
			System.out.println(u.getUserID() + " : " + u.getUsername());
			System.out.println(u.getFirstName() + " " + u.getLastName() + "\n");
		}
	}
	
	//--FUNCTIONALITY--//
	public void addUser(User user) {
		
	}
	
	public void removeUser(User user) {
		
	}
	
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
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
