

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import banking.Application;
import banking.exceptions.*;
import banking.model.*;
import banking.menus.*;
import banking.menus.standard_user.*;
import banking.menus.super_user.*;

public class MenuTest {
	private static final Application app = new Application();
	
	/*
	 * MENU RELATED TEST
	 * */
	
	//GENERAL
	@Test
	public void UnknownCommandTest() throws ExitingException {
		Menu.navigationHistory.get(Menu.navigationHistory.size() -1).parseCommand("unknown command");
		
	}
	
	@Test(expected = ExitingException.class)
	public void ExitingApplicationTest() throws ExitingException {
		Menu.navigationHistory.get(Menu.navigationHistory.size() -1).parseCommand("exit");
	}
	
	@Test
	public void TooManyArgumentsTest() throws ExitingException {
		assertFalse(Menu.navigationHistory.get(Menu.navigationHistory.size() -1).parseCommand("too many arguments for this page"));
	}
	
	//NAVIGATION
	@Test
	public void NavigateToNewUserMenuTest() {
		app.addMenu(NewUserMenu.getMenu());
		assertTrue(app.getCurrentMenu().getName().equals(NewUserMenu.getMenu().getName()));
	}
	
	@Test
	public void NavigateToSignInMenuTest() throws ExitingException {
		assertTrue(app.getCurrentMenu().getName().equals(SignInMenu.getMenu().getName()));
		app.getCurrentMenu().parseCommand("sign-out");
	}
	
	@Test
	public void NavigateToAccountsMenuTest() {
		app.addMenu(AccountsMenu.getMenu());
		assertTrue(app.getCurrentMenu().getName().equals(AccountsMenu.getMenu().getName()));
	}
	
	@Test
	public void NavigateToEditUserInfoMenuTest() {
		app.addMenu(EditUserInfoMenu.getMenu());
		assertTrue(app.getCurrentMenu().getName().equals(EditUserInfoMenu.getMenu().getName()));
	}
	
	@Test
	public void NavigateToHomeMenuTest() {
		app.addMenu(HomeMenu.getMenu());
		assertTrue(app.getCurrentMenu().getName().equals(HomeMenu.getMenu().getName()));
	}
	
	@Test
	public void NavigateToSingleAccountMenuTest() {
		app.addMenu(SingleAccountMenu.getMenu());
		assertTrue(app.getCurrentMenu().getName().equals(SingleAccountMenu.getMenu().getName()));
	}
	
	@Test
	public void NavigateToEditSuperUserInfoMenuTest() {
		app.addMenu(EditSuperUserInfoMenu.getMenu());
		assertTrue(app.getCurrentMenu().getName().equals(EditSuperUserInfoMenu.getMenu().getName()));
	}
	
	@Test
	public void NavigateToSuperUserHomeMenuTest() {
		app.addMenu(SuperUserHomeMenu.getMenu());
		assertTrue(app.getCurrentMenu().getName().equals(SuperUserHomeMenu.getMenu().getName()));
	}
	
	@Test
	public void NavigateToUsersMenuTest() {
		app.addMenu(UsersMenu.getMenu());
		assertTrue(app.getCurrentMenu().getName().equals(UsersMenu.getMenu().getName()));
	}
	
	@Test
	public void MultipleMenusTest() throws ExitingException{
		app.getCurrentMenu().parseCommand("sign-out");
		app.addMenu(EditUserInfoMenu.getMenu());
		app.addMenu(EditUserInfoMenu.getMenu());
		app.addMenu(EditUserInfoMenu.getMenu());
		app.addMenu(EditUserInfoMenu.getMenu());
		app.addMenu(EditUserInfoMenu.getMenu());
		app.getCurrentMenu().parseCommand("trail");
		assertTrue(app.getMenus().size() == 6);
	}
	
	@Test
	public void SignOutReturnsToSignInMenuTest() throws ExitingException {
		app.addMenu(EditUserInfoMenu.getMenu());
		app.addMenu(EditUserInfoMenu.getMenu());
		app.addMenu(EditUserInfoMenu.getMenu());
		app.addMenu(EditUserInfoMenu.getMenu());
		app.addMenu(EditUserInfoMenu.getMenu());
		
		app.getCurrentMenu().parseCommand("trail");
		app.getCurrentMenu().parseCommand("sign-out");
		assertTrue(app.getMenus().size() == 1 && app.getCurrentMenu().getName().equals(SignInMenu.getMenu().getName()));
	}
	
	
	//MENU
	@Test
	public void SignOutUserTest() throws ExitingException {
		app.currentSuperUser = new SuperUser();
		app.currentUser = new User();
		
		app.addMenu(NewUserMenu.getMenu());
		
		app.getCurrentMenu().parseCommand("sign-out");
		
		assertTrue((app.currentSuperUser == null) && (app.currentUser == null));
	}
	
	@Test
	public void SignOutUserWithBackTest() throws ExitingException {
		app.currentSuperUser = new SuperUser();
		app.currentUser = new User();
		
		app.addMenu(EditUserInfoMenu.getMenu());
		app.addMenu(HomeMenu.getMenu());
		app.addMenu(EditUserInfoMenu.getMenu());
		app.addMenu(EditUserInfoMenu.getMenu());
		
		
		app.getCurrentMenu().parseCommand("back");
		app.getCurrentMenu().parseCommand("back");
		app.getCurrentMenu().parseCommand("back");
		
		assertTrue(app.currentSuperUser != null && app.currentUser == null);
		app.getCurrentMenu().parseCommand("sign-out");
	}
	
	@Test
	public void DoesntSignOutUserWithBackTest() throws ExitingException {
		app.currentSuperUser = new SuperUser();
		app.currentUser = new User();
		
		app.addMenu(EditUserInfoMenu.getMenu());
		app.addMenu(HomeMenu.getMenu());
		app.addMenu(EditUserInfoMenu.getMenu());
		
		
		app.getCurrentMenu().parseCommand("back");
		
		assertTrue(app.currentSuperUser != null && app.currentUser != null);
		app.getCurrentMenu().parseCommand("sign-out");
	}
}
