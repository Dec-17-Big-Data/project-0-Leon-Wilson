package banking.menus;

public class NewUserMenu extends Menu {
	protected String commands ="";
	private static Menu menu = null;
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new NewUserMenu();
		}
		return menu;
	}
	
	private NewUserMenu() {
		
	}
}
