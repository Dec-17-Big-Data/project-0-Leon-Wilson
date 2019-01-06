package banking.menus.standard_user;

import banking.menus.Menu;

public class SingleAccountMenu extends Menu {
	protected String commands ="((display-(account-summary|(all|pending|posted)?-transactions|balance)|withdraw|deposit))";
	private static Menu menu = null;
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new SingleAccountMenu();
		}
		return menu;
	}
	
	private SingleAccountMenu() {
		
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
