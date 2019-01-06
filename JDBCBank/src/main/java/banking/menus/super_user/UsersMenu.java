package banking.menus.super_user;

import banking.menus.Menu;

public class UsersMenu extends Menu {
	protected String commands ="(create|delete|access)-user|display-user-list";
	private static Menu menu = null;
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new UsersMenu();
		}
		return menu;
	}
	
	private UsersMenu() {
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getHelp() {
		// TODO Auto-generated method stub
		
	}
}
