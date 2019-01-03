package banking.menus.standard_user;

import banking.menus.Menu;

public class EditUserInfoMenu extends Menu {
	protected String commands ="(edit-(((first|last)-name)|password|(phone-number)))";
	private static Menu menu = null;
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new EditUserInfoMenu();
		}
		return menu;
	}
	
	private EditUserInfoMenu() {
		
	}
}
