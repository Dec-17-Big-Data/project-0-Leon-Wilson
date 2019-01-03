package banking.menus.standard_user;

import banking.menus.Menu;

public class AccountsMenu extends Menu {
	protected String commands ="(((delete|add|access|display)-account(-list)?)|transfer)";
	private static Menu menu = null;
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new AccountsMenu();
		}
		return menu;
	}
	
	private AccountsMenu() {
		
	}
}
