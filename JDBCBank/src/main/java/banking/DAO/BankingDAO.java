package banking.DAO;

import java.util.List;
import java.util.Optional;

import banking.model.*;

public interface BankingDAO {
	
	
	//--USERS--//
	Optional<User> signInUser(String username, String password);
	boolean signUpUser(User user);
	
	//--ACCOUNTS--//
	Optional<List<Account>> getUsersAccounts(Integer userID);
	Optional<Account> getAccount(Integer accountID);
	
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
	boolean deposit(Integer accountID, Double amount);
	boolean withdrawl(Integer accountID, Double amount);
	
	
	//--ERRORS--//
	Optional<List<ErrorLogs>> getErrors(Integer threshold);
	
	
}
