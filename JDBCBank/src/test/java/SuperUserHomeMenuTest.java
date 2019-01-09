import static org.junit.Assert.assertTrue;

import org.junit.Test;

import banking.exceptions.ExitingException;
import banking.menus.Menu;
import banking.menus.standard_user.HomeMenu;
import banking.menus.super_user.EditSuperUserInfoMenu;
import banking.menus.super_user.SuperUserHomeMenu;
import banking.menus.super_user.UsersMenu;

public class SuperUserHomeMenuTest {
	@Test
	public void EditSuperUserNavigatesToEditSuperUserInfoMenuTest() throws ExitingException {
		Menu.navigationHistory.add(SuperUserHomeMenu.getMenu());
		SuperUserHomeMenu m = (SuperUserHomeMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		m.parseCommand("edit-super-user");
		assertTrue(Menu.navigationHistory.get(Menu.navigationHistory.size() - 1).getName().equals(EditSuperUserInfoMenu.getMenu().getName()));
	}
	
	@Test
	public void AccessUserListNavigatesToUserMenuTest() throws ExitingException {
		Menu.navigationHistory.add(SuperUserHomeMenu.getMenu());
		SuperUserHomeMenu m = (SuperUserHomeMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		m.parseCommand("access-user-list");
		assertTrue(Menu.navigationHistory.get(Menu.navigationHistory.size() - 1).getName().equals(UsersMenu.getMenu().getName()));
	}
}
