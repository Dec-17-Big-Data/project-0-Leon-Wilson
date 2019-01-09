import static org.junit.Assert.assertTrue;

import org.junit.Test;

import banking.exceptions.ExitingException;
import banking.menus.Menu;
import banking.menus.standard_user.AccountsMenu;
import banking.menus.standard_user.EditUserInfoMenu;
import banking.menus.standard_user.HomeMenu;

public class HomeMenuTest {
	@Test
	public void AccessAccountListNavigatesToAccountsMenuTest() throws ExitingException {
		Menu.navigationHistory.add(HomeMenu.getMenu());
		HomeMenu m = (HomeMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		m.parseCommand("access-account-list");
		assertTrue(Menu.navigationHistory.get(Menu.navigationHistory.size() - 1).getName().equals(AccountsMenu.getMenu().getName()));
	}
	
	@Test
	public void EditUserInfoNavigatesToEditUserInfoMenuTest() throws ExitingException {
		Menu.navigationHistory.add(EditUserInfoMenu.getMenu());
		EditUserInfoMenu m = (EditUserInfoMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		m.parseCommand("edit-user-info");
		assertTrue(Menu.navigationHistory.get(Menu.navigationHistory.size() - 1).getName().equals(EditUserInfoMenu.getMenu().getName()));
	}
}
