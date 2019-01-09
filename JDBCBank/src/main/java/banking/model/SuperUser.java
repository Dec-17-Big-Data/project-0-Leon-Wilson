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
	}
	
	//--EXISTING SUPER USER--//
	public SuperUser(Integer userID, String username, String password, ArrayList<User> users) {
		this.userID = userID;
		this.username = username;
		this.setPassword(password);
		this.setUsers(users);
	}
	
//---FUNCTIONS---//
	
	public void displayAllUser() {
		for(User u : users) {
			System.out.println(u.getUserID() + " : " + u.getUsername());
			System.out.println(u.getFirstName() + " " + u.getLastName() + "\n");
		}
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
