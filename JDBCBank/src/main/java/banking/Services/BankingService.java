package banking.Services;

import java.util.List;
import java.util.Optional;

import banking.DAO.BankingDAO;
import banking.DAO.BankingOracle;
import banking.model.AccountTypes;
import banking.model.SuperUser;
import banking.model.User;

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
	
	public Optional<SuperUser> signInSuperUser(String username, String password){
		return bankingDAO.signInSuperUser(username, password);
	}
	
	public Optional<User> getUser(Integer userID){
		return bankingDAO.getUser(userID);
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
	public boolean updateUsername(Integer userID, String username) {
		return bankingDAO.updateUsername(userID, username);
	}
	
	public boolean addNewAccount(Integer userID, AccountTypes type, Double balance, String accountName) {
		return bankingDAO.createNewAccount(userID, type, balance, accountName);
	}
	
	public boolean deleteAccount(Integer accountID) {
		return bankingDAO.deleteExistingAccount(accountID);
	}
	
	public boolean updateSuperUsername(Integer userID, String username) {
		return bankingDAO.updateSuperUsername(userID, username);
	}
	
	public boolean updateSuperPassword(Integer userID, String userPassword) {
		return bankingDAO.updateSuperPassword(userID, userPassword);
	}
	
	public boolean checkSuperUsernameAvailability(String username) {
		return bankingDAO.checkSuperUsernameAvailability(username);
	}
	
	public boolean addSuperUser(String username, String password) {
		return bankingDAO.addSuperUser(username, password);
	}
	
	public boolean deleteUser(Integer userID) {
		return bankingDAO.deleteUser(userID);
	}
}
