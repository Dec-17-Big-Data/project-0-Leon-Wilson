package banking.menus.super_user;

import banking.menus.Menu;

public class SuperUserHomeMenu extends Menu {
	protected String commands ="(((edit|create)-super-user)|access-user-list)";
	private static Menu menu = null;
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new SuperUserHomeMenu();
		}
		return menu;
	}
	
	private SuperUserHomeMenu() {
		
	}
}
