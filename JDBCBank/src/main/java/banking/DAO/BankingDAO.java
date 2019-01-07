package banking.DAO;

import java.util.List;
import java.util.Optional;

import banking.model.*;

public interface BankingDAO {
	
	
	//--USERS--//
	Optional<User> signInUser(String username, String password);
	boolean signUpUser(User user);
	boolean createNewUser(String firstName, String lastName, String username,String phoneNumber, String password);
	boolean checkUsernameAvailability(String username);
	boolean updateFirstName(Integer userID, String firstName);
	boolean updateLastName(Integer userID, String lastName);
	boolean updatePhoneNumber(Integer userID, String phoneNumber);
	boolean updatePassword(Integer userID, String userPassword);
	
	//--ACCOUNTS--//
	Optional<List<Account>> getUsersAccounts(Integer userID);
	Optional<Account> getAccount(Integer accountID);
	boolean createNewAccount(Integer userID, AccountTypes type, Double balance, String accountName);
	boolean deleteExistingAccount(Integer accountID);
	
	//--CARDS--//
	Optional<List<ChargeCard>> getAllCards(Integer accountID);
	Optional<ChargeCard> getAccountCard(Integer accountID);
	
	//--SUPER USER--//
	Optional<List<User>> getAllUsers();
	Optional<User> getUser(Integer userID);
	Optional<SuperUser> signInSuperUser(String username, String password);
	boolean signUpSuperUser(SuperUser user);
	
	//--TRANSACTIONS--//
	Optional<List<Transaction>> getUserTransactions();
	
	//--TRANSFERS--//
	boolean transfer();
	
	//--DEPOSITS & WITHDRAWLS--//
	boolean updateBalance(Integer accountID, Double balance);
	
	//--ERRORS--//
	Optional<List<ErrorLogs>> getErrors(Integer threshold);
	
	
}
