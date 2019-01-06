package banking.menus.standard_user;

import banking.exceptions.ExitingException;
import banking.menus.Menu;

public class SingleCardMenu extends Menu {
	protected String commands ="((display|edit)-card(-(info|pin))?)";
	private static Menu menu = null;
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new SingleCardMenu();
		}
		return menu;
	}
	
	private SingleCardMenu() {
		
	}
	
	public boolean parseCommand(String command) throws ExitingException {
		if(!super.parseCommand(command)) {
			//PARSE UNDER CURRENT MENU
			//IF FOUND
				//TRUE
			
			//FALSE
			return false;
		}
		
		return true;
	}
	
	public String getName() {
		return "";
	}

	@Override
	public void getHelp() {
		// TODO Auto-generated method stub
		
	}
}
