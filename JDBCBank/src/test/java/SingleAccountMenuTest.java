import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import banking.Application;
import banking.exceptions.account_exceptions.OverdraftException;
import banking.menus.Menu;
import banking.menus.standard_user.SingleAccountMenu;
import banking.model.Account;
import banking.model.AccountTypes;
import banking.model.User;

public class SingleAccountMenuTest {

	@Test
	public void WithdrawNegativeNumberTest() {
		Application.currentUser = new User();
		Application.currentUser.setAccessedAccount(new Account("",0,0,AccountTypes.checking, 500.00D));
		Menu.navigationHistory.add(SingleAccountMenu.getMenu());
		
		SingleAccountMenu m = (SingleAccountMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		assertFalse(m.withdraw(-3.0));
	}
	
	@Test
	public void WithdrawOverdraftTest(){
		Application.currentUser = new User();
		Application.currentUser.setAccessedAccount(new Account("",0,0,AccountTypes.checking, 500.00D));
		Menu.navigationHistory.add(SingleAccountMenu.getMenu());
		
		SingleAccountMenu m = (SingleAccountMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		assertFalse(m.withdraw(600.0));
	}
	
	@Test
	public void DepositNegativeNumberTest() {
		Application.currentUser = new User();
		Application.currentUser.setAccessedAccount(new Account("",0,0,AccountTypes.checking, 500.00D));
		Menu.navigationHistory.add(SingleAccountMenu.getMenu());
		
		SingleAccountMenu m = (SingleAccountMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		assertFalse(m.deposit(-2.0));
	}
}
