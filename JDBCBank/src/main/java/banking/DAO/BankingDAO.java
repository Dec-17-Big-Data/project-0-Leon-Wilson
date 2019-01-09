package banking.DAO;

import java.util.List;
import java.util.Optional;

import banking.model.AccountTypes;
import banking.model.SuperUser;
import banking.model.User;

public interface BankingDAO {
	
	
	//--USERS--//
	Optional<User> signInUser(String username, String password);
	boolean createNewUser(String firstName, String lastName, String username,String phoneNumber, String password);
	boolean checkUsernameAvailability(String username);
	boolean updateFirstName(Integer userID, String firstName);
	boolean updateLastName(Integer userID, String lastName);
	boolean updatePhoneNumber(Integer userID, String phoneNumber);
	boolean updatePassword(Integer userID, String userPassword);
	boolean updateUsername(Integer userID, String username);
	
	//--ACCOUNTS--//
	boolean createNewAccount(Integer userID, AccountTypes type, Double balance, String accountName);
	boolean deleteExistingAccount(Integer accountID);

	//--SUPER USER--//
	Optional<List<User>> getAllUsers();
	Optional<User> getUser(Integer userID);
	Optional<SuperUser> signInSuperUser(String username, String password);
	boolean updateSuperPassword(Integer userID, String userPassword);
	boolean updateSuperUsername(Integer userID, String username);
	boolean checkSuperUsernameAvailability(String username);
	boolean addSuperUser(String username, String password);
	boolean deleteUser(Integer userID);

	//--DEPOSITS & WITHDRAWLS--//
	boolean updateBalance(Integer accountID, Double balance);
}
