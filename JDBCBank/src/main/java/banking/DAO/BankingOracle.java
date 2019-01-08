package banking.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import banking.Application;
import banking.ConnectionUtil;
import banking.exceptions.sign_up_exceptions.UsernameAlreadyExistException;
import banking.model.*;

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
	public Optional<SuperUser> signInSuperUser(String username, String password) {
		SuperUser user;
		Connection con = ConnectionUtil.getConnection();
		CallableStatement callableStatement = null;
		
		String sql = "{call SIGN_IN_SUPER_USER(?,?,?)}";
		
		if(con == null) {
			return Optional.empty();
		}
		
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setString(1, username);
			callableStatement.setString(2, password);
			callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
			
			callableStatement.executeUpdate();
			ArrayList<User> users;
			Integer userID = callableStatement.getInt(3);
			try {
				users = (ArrayList<User>) getAllUsers().get();
			} catch (NoSuchElementException e) {
				users = new ArrayList<User>();
			}
			user = new SuperUser(userID, username, password, users);
			return Optional.of(user);
		}catch(SQLException e) {
			
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<User> signInUser(String username, String password) {
		User user;
		Set<Account> usersAccounts = new HashSet<Account>();
		Connection con = ConnectionUtil.getConnection();
		CallableStatement callableStatement = null;
		String sql = "{call SIGN_IN_USER(?,?,?,?,?,?)}";
		
		
		if(con == null) {
			return Optional.empty();
		}
		
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setString(1, username);
			callableStatement.setString(2, password);
			callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
			callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
			
			callableStatement.executeUpdate();
			
			Integer userID = callableStatement.getInt(3);
			String firstName = callableStatement.getString(4);
			String lastName = callableStatement.getString(5);
			String phoneNumber = callableStatement.getString(6);
			
			String accountSQL = "select * from accounts where user_id = ?";
			Connection con2 = ConnectionUtil.getConnection();
			
			PreparedStatement ps = con2.prepareStatement(accountSQL);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				/*String name, Integer accountID, Integer userID, 
				 * AccountTypes type, Double balance, List<Transaction> pendingTransactions, 
				 * List<Transaction> transactionHistory
				*/
				AccountTypes type;
				switch(rs.getString("account_type")) {
				case "checking":
					type = AccountTypes.checking;
					break;
				case "savings":
					type = AccountTypes.savings;
					break;
				default:
					type = null;
					break;
				}
				usersAccounts.add(new Account(rs.getString("account_name"),rs.getInt("account_id"),rs.getInt("user_id"), type, rs.getDouble("balance"), new ArrayList<Transaction>(), new ArrayList<Transaction>()));
				
				//System.out.println(rs.getInt("account_id")+ " " +rs.getInt("user_id")+ " " +rs.getString("account_type")+ " " +rs.getFloat("balance")+ " " +rs.getString("account_name"));
			}
			user = new User(userID, firstName,lastName,username, phoneNumber,password, usersAccounts,new HashSet<ChargeCard>());
			//MIGH UNDO //Application.currentUser = user;
			//System.out.println(user.toString());
			return Optional.of(user);
		}catch(SQLException e) {
			System.out.println("Couldn't sign in user");
		}
		
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
	public Optional<List<ErrorLogs>> getErrors(Integer threshold) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBalance(Integer accountID, Double balance) {
		Connection con = ConnectionUtil.getConnection();
		if(con == null) {
			return false;
		}
		
		CallableStatement callableStatement = null;
		String sql = "{call UPDATE_ACCOUNT_BALANCE(?,?)}";
		
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setInt(1, accountID);
			callableStatement.setDouble(2, balance);
			
			callableStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateFirstName(Integer userID, String firstName) {
		Connection con = ConnectionUtil.getConnection();
		if(con == null) {
			return false;
		}
		
		CallableStatement callableStatement = null;
		String sql = "{call UPDATE_FIRST_NAME(?,?)}";
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setInt(1, userID);
			callableStatement.setString(2, firstName);
			
			callableStatement.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			
		}
		
		return false;
	}

	@Override
	public boolean updateLastName(Integer userID, String lastName) {
		Connection con = ConnectionUtil.getConnection();
		if(con == null) {
			return false;
		}
		
		CallableStatement callableStatement = null;
		String sql = "{call UPDATE_LAST_NAME(?,?)}";
		
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setInt(1, userID);
			callableStatement.setString(2, lastName);
			
			callableStatement.executeUpdate();
			return true;
		} catch(SQLException e) {
			
		}
		return false;
	}

	@Override
	public boolean updatePhoneNumber(Integer userID, String phoneNumber) {
		Connection con = ConnectionUtil.getConnection();
		if(con == null) {
			return false;
		}
		
		CallableStatement callableStatement = null;
		String sql = "{call UPDATE_PHONE_NUMBER(?,?)}";
		
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setInt(1, userID);
			callableStatement.setString(2, phoneNumber);
			
			callableStatement.executeUpdate();
			return true;
		} catch(SQLException e) {
			
		}
		return false;
	}

	@Override
	public boolean updatePassword(Integer userID, String userPassword) {
		Connection con = ConnectionUtil.getConnection();
		if(con == null) {
			return false;
		}
		
		CallableStatement callableStatement = null;
		String sql = "{call UPDATE_PASSWORD(?,?)}";
		
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setInt(1, userID);
			callableStatement.setString(2, userPassword);
			
			callableStatement.executeUpdate();
			return true;
		} catch(SQLException e) {
			
		}
		return false;
	}
	
	@Override
	public boolean updateUsername(Integer userID, String username) {
		Connection con = ConnectionUtil.getConnection();
		if(con == null) {
			return false;
		}
		
		CallableStatement callableStatement = null;
		String sql = "{call UPDATE_USERNAME(?,?)}";
		
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setInt(1, userID);
			callableStatement.setString(2, username);
			
			callableStatement.executeUpdate();
			return true;
		} catch(SQLException e) {
			
		}
		return false;
	}

	@Override
	public boolean createNewAccount(Integer userID, AccountTypes type, Double balance, String accountName) {
		Connection con = ConnectionUtil.getConnection();
		User u = Application.currentUser;
		if(con == null) {
			return false;
		}
		
		
		CallableStatement callableStatement = null;
		String sql = "{call ADD_ACCOUNT(?,?,?,?,?)}";
		String t;
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setInt(1, userID);
			switch(type) {
			case checking:
				t = "checking";
				break;
			case savings:
				t = "savings";
				break;
			default:
				t = "";
				break;
			}
			
			if(t == "") {
				//throw exception
			}
			
			callableStatement.setString(2, t);
			callableStatement.setDouble(3, balance);
			callableStatement.setString(4, accountName);
			callableStatement.registerOutParameter(5,java.sql.Types.INTEGER);
			
			callableStatement.executeUpdate();
			Integer accountID = callableStatement.getInt(5);
			//String name, Integer accountID, Integer userID, AccountTypes type, Double startingBalance
			u.addAccount(new Account(accountName, accountID, userID, type, balance));
			return true;
			
		} catch(SQLException e) {
			return false;
		}
	}
	
	@Override
	public boolean deleteExistingAccount(Integer accountID) {
		Connection con = ConnectionUtil.getConnection();
		if(con == null) {
			return false;
		}
		
		CallableStatement callableStatement = null;
		String sql = "{call REMOVE_ACCOUNT(?)}";
		
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setInt(1, accountID);
			
			callableStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean createNewUser(String firstName, String lastName, String username,String phoneNumber, String password) {
		Connection con = ConnectionUtil.getConnection();
		if(con == null) {
			return false;
		}
		
		CallableStatement callableStatement = null;
		String sql = "{call ADD_USER(?,?,?,?,?,?)}";
		
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setString(1,firstName);
			callableStatement.setString(2,lastName);
			callableStatement.setString(3,username);
			callableStatement.setString(4,phoneNumber);
			callableStatement.setString(5,password);
			callableStatement.registerOutParameter(6,java.sql.Types.INTEGER);

			callableStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean checkUsernameAvailability(String username) {
		Connection con = ConnectionUtil.getConnection();

		if (con == null) {
			return false;
		}

		try {
			String sql = "select * from users where username = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.isBeforeFirst()) {
				throw new UsernameAlreadyExistException();
			}
			
			return true;
		} catch (SQLException e) {
			System.out.println("There was a problem when attempting to connect to the database.");
			return false;
		} catch (UsernameAlreadyExistException e) {
			System.out.println("That username already exist");
			return false;
		}
	}

	@Override
	public boolean updateSuperPassword(Integer userID, String userPassword) {
		Connection con = ConnectionUtil.getConnection();
		if(con == null) {
			return false;
		}
		
		CallableStatement callableStatement = null;
		String sql = "{call UPDATE_SUPER_PASSWORD(?,?)}";
		
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setInt(1, userID);
			callableStatement.setString(2, userPassword);
			
			callableStatement.executeUpdate();
			return true;
		} catch(SQLException e) {
			
		}
		return false;
	}

	@Override
	public boolean updateSuperUsername(Integer userID, String username) {
		Connection con = ConnectionUtil.getConnection();
		if(con == null) {
			return false;
		}
		
		CallableStatement callableStatement = null;
		String sql = "{call UPDATE_SUPER_USERNAME(?,?)}";
		
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setInt(1, userID);
			callableStatement.setString(2, username);
			
			callableStatement.executeUpdate();
			return true;
		} catch(SQLException e) {
			
		}
		return false;
	}

	@Override
	public boolean checkSuperUsernameAvailability(String username) {
		Connection con = ConnectionUtil.getConnection();

		if (con == null) {
			return false;
		}

		try {
			String sql = "select * from super_users where username = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.isBeforeFirst()) {
				throw new UsernameAlreadyExistException();
			}
			
			return true;
		} catch (SQLException e) {
			System.out.println("There was a problem when attempting to connect to the database.");
			return false;
		} catch (UsernameAlreadyExistException e) {
			System.out.println("That username already exist");
			return false;
		}
	}

	@Override
	public boolean addSuperUser(String username, String password) {
		Connection con = ConnectionUtil.getConnection();
		if(con == null) {
			return false;
		}
		
		CallableStatement callableStatement = null;
		String sql = "{call ADD_SUPER_USER(?,?)}";
		
		try {
			callableStatement = con.prepareCall(sql);
			callableStatement.setString(1,username);
			callableStatement.setString(2,password);

			callableStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
