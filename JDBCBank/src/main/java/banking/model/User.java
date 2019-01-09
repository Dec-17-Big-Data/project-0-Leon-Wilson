package banking.model;

import java.util.Set;

public class User {

	@Override
	public String toString() {
		return "User [userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", phoneNumber=" + phoneNumber + ", password=" + password + ", accounts=" + accounts
				+ ", cards=" + cards + "]";
	}

	//List of accounts
	private Integer userID;
	private String firstName;
	private String lastName;
	private String username;
	private String phoneNumber;
	private String password;
	private Set<Account> accounts;
	private Set<ChargeCard> cards;
	
	private static Account accessedAccount;
	
//---CONSTRUCTORS---//
	
	public User() {
		
	}
	
	public User(Integer userID, String firstName, String lastName, String username, String phoneNumber, String password, Set<Account> accounts, Set<ChargeCard> cards) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.setPhoneNumber(phoneNumber);
		this.password = password;
		this.setAccounts(accounts);
		this.setCards(cards);
		}
	
//---FUNCTIONS---//
	
	/***
	 * @author Leon Wilson
	 */
	public void addAccount(Account a) {
		this.accounts.add(a);
	}
	
	//--GETTERS/SETTERS--//
	
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}
	
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Set<ChargeCard> getCards() {
		return cards;
	}

	public void setCards(Set<ChargeCard> cards) {
		this.cards = cards;
	}

	public static Account getAccessedAccount() {
		return accessedAccount;
	}

	public static void setAccessedAccount(Account accessedAccount) {
		User.accessedAccount = accessedAccount;
	}
	
}
