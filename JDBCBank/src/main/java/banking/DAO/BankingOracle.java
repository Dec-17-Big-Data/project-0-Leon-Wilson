package banking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import banking.ConnectionUtil;
import banking.model.Account;
import banking.model.ChargeCard;
import banking.model.ErrorLogs;
import banking.model.SuperUser;
import banking.model.Transaction;
import banking.model.User;

public class BankingOracle implements BankingDAO {
	private static BankingOracle bankingOracle = null;
	
	public static BankingDAO getDAO() {
		if(bankingOracle == null) {
			bankingOracle = new BankingOracle();
		}
		return bankingOracle;
	}
	
	private BankingOracle() {
		
	}
	
	
	public Optional<List<User>> getAllUsers() {
		List<User> listOfUsers = new ArrayList<User>();
		Connection con = ConnectionUtil.getConnection();

		if (con == null) {
			return Optional.empty();
		}

		try {
			String sql = "select * from users";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listOfUsers.add(new User(rs.getInt("user_id"), rs.getString("first_name"),rs.getString("last_name"), rs.getString("username"), rs.getString("phone_number"),rs.getString("user_password"),new HashSet<Account>(),new HashSet<ChargeCard>()));
			}
			
			return Optional.of(listOfUsers);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<User> getUser(Integer userID) {
		User user;
		Connection con = ConnectionUtil.getConnection();
		return null;
	}

	@Override
	public Optional<User> signInUser(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean signUpUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<List<Account>> getUsersAccounts(Integer userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Account> getAccount(Integer accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<ChargeCard>> getAllCards(Integer accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ChargeCard> getAccountCard(Integer accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SuperUser> signInSuperUser(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean signUpSuperUser(SuperUser user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<List<Transaction>> getUserTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean transfer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deposit(Integer accountID, Double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdrawl(Integer accountID, Double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<List<ErrorLogs>> getErrors(Integer threshold) {
		// TODO Auto-generated method stub
		return null;
	}

}
