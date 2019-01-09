import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import banking.Application;
import banking.exceptions.ExitingException;
import banking.menus.Menu;
import banking.menus.super_user.UsersMenu;
import banking.model.Account;
import banking.model.ChargeCard;
import banking.model.SuperUser;
import banking.model.User;

public class UsersMenuTest {
	@Test
	public void DeleteUserDoesntExistTest() throws ExitingException {
		Menu.navigationHistory.add(UsersMenu.getMenu());
		Application.currentSuperUser = new SuperUser();
		Application.currentSuperUser.setUsers(new ArrayList<User>());
		User u1 = new User(0, "Leon", "Wilson", "lwilson", "555-555-5555", "password11111", new HashSet<Account>(), new HashSet<ChargeCard>());
		User u2 = new User(1, "Tyler", "Wilson", "twilson", "444-444-4444", "password22222", new HashSet<Account>(), new HashSet<ChargeCard>());
		User u3 = new User(2, "Amber", "Wilson", "awilson", "666-666-6666", "password33333", new HashSet<Account>(), new HashSet<ChargeCard>());
		
		Application.currentSuperUser.getUsers().add(u1);
		Application.currentSuperUser.getUsers().add(u2);
		Application.currentSuperUser.getUsers().add(u3);
		
		UsersMenu m = (UsersMenu)Menu.navigationHistory.get(Menu.navigationHistory.size() - 1);
		assertTrue(m.parseCommand("delete-user 4"));
	}
}
