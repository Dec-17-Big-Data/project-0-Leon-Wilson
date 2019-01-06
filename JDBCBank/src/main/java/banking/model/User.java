package banking.model;


import java.util.HashSet;
import java.util.Set;

public class User {

	//List of accounts
	private Integer userID;
	private String firstName;
	private String lastName;
	private String username;
	private String phoneNumber;
	private String password; //encrypt?
	private Set<Account> accounts;
	private Set<ChargeCard> cards;
	
//---CONSTRUCTORS---//
	
	//--NEW USER--//
	
	/***
	 * @author Leon Wilson
	 */
	public User(Integer userID, String firstName, String lastName, String username, String phoneNumber, String password) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.setPhoneNumber(phoneNumber);
		this.password = password;
		this.accounts = new HashSet<Account>();
		this.accounts.add(new Account()); //default account
		//no card
	}
	
	/***
	 * @author Leon Wilson
	 */
	public User(Integer userID, String firstName, String lastName, String username, String phoneNumber, String password, Account newAccount) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.setPhoneNumber(phoneNumber);
		this.password = password;
		this.accounts = new HashSet<Account>();
		this.accounts.add(newAccount);
		this.setCards(new HashSet<ChargeCard>());
		//no card
	}
	
	/***
	 * @author Leon Wilson
	 */
	public User(Integer userID, String firstName, String lastName, String username, String phoneNumber, String password, ChargeCard card) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.setPhoneNumber(phoneNumber);
		this.password = password;
		this.accounts = new HashSet<Account>();
		this.accounts.add(new Account()); //default account
		this.setCards(new HashSet<ChargeCard>());	
		this.getCards().add(card);
		}
	
	/***
	 * @author Leon Wilson
	 */
	public User(Integer userID, String firstName, String lastName, String username, String phoneNumber, String password, Account newAccount, ChargeCard card) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.setPhoneNumber(phoneNumber);
		this.password = password;
		this.accounts = new HashSet<Account>();
		this.accounts.add(newAccount);
		this.setCards(new HashSet<ChargeCard>());
		this.getCards().add(card);
		}
	
	//--EXISTING USER--//
	
	/***
	 * @author Leon Wilson
	 */
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
	
	//--DISPLAY--//
	
	/***
	 * @author Leon Wilson
	 */
	public void displayAccounts() {
		
	}
	
	/***
	 * @author Leon Wilson
	 */
	public void displayUserSummary() {
		
	}
	
	public void displayCards() {
		
	}

	//--FUNCTIONALITY--//
	
	/***
	 * @author Leon Wilson
	 */
	public void addAccount(String name, AccountTypes type) {
		
	}
	
	/***
	 * @author Leon Wilson
	 */
	public void addAccount(String name, AccountTypes type, Double initialBalance) {
		
	}
	
	/***
	 * @author Leon Wilson
	 */
	public void deleteAccount(Account a1) {
		
	}
	
	/***
	 * @author Leon Wilson
	 */
	public void transferBetweenAccounts(Account a1, Account a2, Double amount) {
		
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
	
}
