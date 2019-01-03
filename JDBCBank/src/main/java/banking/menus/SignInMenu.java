package banking.menus;

import java.util.List;

public class SignInMenu extends Menu {
	private String commands ="(sign-(in(-admin)?|up))";
	private Integer maxArguments = 3;
	private static Menu menu = null;
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new SignInMenu();
		}
		return menu;
	}
	private SignInMenu() {
		
	}
	
	protected boolean parseCommand(String command) {
		if(!super.parseCommand(command)) {
			if(tooManyArguments(command,maxArguments)) {
				return false;
			}
			
			
			//PARSE UNDER CURRENT MENU
			//IF FOUND
				//TRUE
			
			//FALSE
			return false;
		}
		
		return true;
	}
}
