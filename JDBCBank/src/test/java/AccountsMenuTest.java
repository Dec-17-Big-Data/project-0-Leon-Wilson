import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import banking.Application;
import banking.exceptions.ExitingException;
import banking.menus.Menu;
import banking.menus.standard_user.AccountsMenu;
import banking.menus.standard_user.SingleAccountMenu;
import banking.model.Account;
import banking.model.AccountTypes;
import banking.model.User;

public class AccountsMenuTest {

	@Test
	public void DeleteAccountDoesntExistTest() throws ExitingException {
		Application.currentUser =new User();
		Application.currentUser.setAccounts(new HashSet<Account>());
		Application.currentUser.addAccount(new Account("",0,0,AccountTypes.checking, 500.00D));
		Application.currentUser.addAccount(new Account("",1,0,AccountTypes.checking, 500.00D));
		
		Menu.navigationHistory.add(AccountsMenu.getMenu());
		AccountsMenu m = (AccountsMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		assertFalse(m.parseCommand("delete-account 9"));
	}
	
	@Test
	public void DeleteNotEnoughArgumentsTest() throws ExitingException {
		Application.currentUser =new User();
		Application.currentUser.setAccounts(new HashSet<Account>());
		Application.currentUser.addAccount(new Account("",0,0,AccountTypes.checking, 500.00D));
		Application.currentUser.addAccount(new Account("",1,0,AccountTypes.checking, 500.00D));
		
		Menu.navigationHistory.add(AccountsMenu.getMenu());
		AccountsMenu m = (AccountsMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		assertFalse(m.parseCommand("delete-account"));
	}
	
	@Test
	public void DeleteNumberFormatTest() throws ExitingException {
		Application.currentUser =new User();
		Application.currentUser.setAccounts(new HashSet<Account>());
		Application.currentUser.addAccount(new Account("",0,0,AccountTypes.checking, 500.00D));
		Application.currentUser.addAccount(new Account("",1,0,AccountTypes.checking, 500.00D));
		
		Menu.navigationHistory.add(AccountsMenu.getMenu());
		AccountsMenu m = (AccountsMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		assertFalse(m.parseCommand("delete-account z"));
	}
	
	@Test
	public void AccessAccountDoesntExistTest() throws ExitingException {
		Application.currentUser =new User();
		Application.currentUser.setAccounts(new HashSet<Account>());
		Application.currentUser.addAccount(new Account("",0,0,AccountTypes.checking, 500.00D));
		Application.currentUser.addAccount(new Account("",1,0,AccountTypes.checking, 500.00D));
		
		Menu.navigationHistory.add(AccountsMenu.getMenu());
		AccountsMenu m = (AccountsMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		assertFalse(m.parseCommand("access-account 9"));
	}
	
	@Test
	public void AccessAccountExistNavigatesToIndividualAccountMenuTest() throws ExitingException {
		Application.currentUser =new User();
		Application.currentUser.setAccounts(new HashSet<Account>());
		Application.currentUser.addAccount(new Account("",0,0,AccountTypes.checking, 500.00D));
		Application.currentUser.addAccount(new Account("",1,0,AccountTypes.checking, 500.00D));
		
		Menu.navigationHistory.add(AccountsMenu.getMenu());
		AccountsMenu m = (AccountsMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		m.parseCommand("access-account 0");
		assertTrue(Menu.navigationHistory.get(Menu.navigationHistory.size() - 1).getName().equals(SingleAccountMenu.getMenu().getName()));
	}
}
