package banking.menus.standard_user;

import banking.menus.Menu;

public class CardsMenu extends Menu {
	protected String commands ="((display|access)-card(-list)?)";
	private static Menu menu = null;
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new CardsMenu();
		}
		return menu;
	}
	
	private CardsMenu() {
		
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
