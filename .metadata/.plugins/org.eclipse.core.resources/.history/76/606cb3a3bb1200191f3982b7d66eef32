package banking.Services;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import banking.DAO.BankingDAO;
import banking.DAO.BankingOracle;
import banking.model.*;

public class BankingService {
	private static BankingService bankingService = null;
	final static BankingDAO bankingDAO = BankingOracle.getDAO();
	
	public static BankingService getService() {
		if(bankingService == null) {
			bankingService = new BankingService();
		}
		return bankingService;
	}
	
	private BankingService() {
		
	}
	
	public Optional<List<User>> getAllUsers(){
		return bankingDAO.getAllUsers();
	}
	
	public Optional<User> signInUser(String username, String password){
		return bankingDAO.signInUser(username, password);
	}
	
	public boolean createNewUser(String firstName, String lastName, String username,String phoneNumber, String password) {
		return bankingDAO.createNewUser(firstName, lastName, username, phoneNumber, password);
	}
	
	public boolean checkUsernameAvailability(String username) {
		return bankingDAO.checkUsernameAvailability(username);
	}
	
	public boolean updateBalance(Integer accountID, Double balance) {
		return bankingDAO.updateBalance(accountID, balance);
	}
	
	public boolean updateFirstName(Integer userID, String firstName) {
		return bankingDAO.updateFirstName(userID, firstName);
	}
	
	public boolean updateLastName(Integer userID, String lastName) {
		return bankingDAO.updateLastName(userID, lastName);
	}
	
	public boolean updatePhoneNumber(Integer userID, String phoneNumber) {
		return bankingDAO.updatePhoneNumber(userID, phoneNumber);
	}
	
	public boolean updatePassword(Integer userID, String userPassword) {
		return bankingDAO.updatePassword(userID, userPassword);
	}
	
	public boolean addNewAccount(Integer userID, AccountTypes type, Double balance, String accountName) {
		return bankingDAO.createNewAccount(userID, type, balance, accountName);
	}
	
	public boolean deleteAccount(Integer accountID) {
		return bankingDAO.deleteExistingAccount(accountID);
	}
}
