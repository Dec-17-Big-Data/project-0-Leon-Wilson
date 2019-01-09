package banking.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private String accountName;
	private Integer accountID;
	private Integer userID;
	private AccountTypes type;
	private Double balance;
	private List<Transaction> pendingTransactions;
	private List<Transaction> transactionHistory;

//---CONSTRUCTORS---//
	public Account() {
		
	}
	
	public Account(String name, Integer accountID, Integer userID, AccountTypes type, Double startingBalance) {
		this.accountName = name;
		this.setAccountID(accountID);
		this.setUserID(userID);
		this.type = type;
		this.balance = startingBalance;
		this.pendingTransactions = new ArrayList<Transaction>();
		this.transactionHistory = new ArrayList<Transaction>();
	}
	
	//--EXISTING ACCOUNT--//
	
	public Account(String name, Integer accountID, Integer userID, AccountTypes type, Double balance, List<Transaction> pendingTransactions, List<Transaction> transactionHistory) {
		this.accountName = name;
		this.setAccountID(accountID);
		this.setUserID(userID);
		this.type = type;
		this.balance = balance;
		this.pendingTransactions = pendingTransactions;
		this.transactionHistory = transactionHistory;
	}
	
//---FUNCTIONS---//
	
	//--GETTERS/SETTERS--//
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public AccountTypes getType() {
		return type;
	}
	
	public void setType(AccountTypes type) {
		this.type = type;
	}

	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<Transaction> getPendingTransactions() {
		return pendingTransactions;
	}

	public void setPendingTransactions(List<Transaction> pendingTransactions) {
		this.pendingTransactions = pendingTransactions;
	}

	public List<Transaction> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(List<Transaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
}
