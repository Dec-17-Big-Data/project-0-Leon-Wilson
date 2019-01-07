package banking.menus.standard_user;

import banking.Application;
import banking.exceptions.ExitingException;
import banking.menus.Menu;
import banking.menus.NewUserMenu;
import banking.model.*;

public class HomeMenu extends Menu {
	protected String commands ="((display|edit)-user-info|access-(account|card)-list|add-(debit|credit)-purchase)";
	private Integer maxArguments = 1;
	private static String name = "Home";
	private static Menu menu = null;
	
	//HELP SCREEN
	private String help= Menu.help + "\n\n" + name.toUpperCase() +" MENU HELPER\n"
				+ "This is your User home menu. Here you can view your information and access your accounts.\n"
				+ "\nCOMMANDS\n"
				+ "display-user-info : shows the users information on the screen.\n"
				+ "edit-user-info : navigates to the edit user menu, where you can update your user info.\n"
				+ "access-account-list: navigates to the accounts menu for the specified account.\n"
				+ "----------------";
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new HomeMenu();
		}
		return menu;
	}
	
	private HomeMenu() {
		
	}
	
	public boolean parseCommand(String command) throws ExitingException {
		if(!super.parseCommand(command)) {
			if(tooManyArguments(command,maxArguments)) {
				System.out.println("");
				return false;
			}
			
			//PARSE UNDER CURRENT MENU
			if(command.split(" ")[0].matches(commands)) {
				switch(command.split(" ")[0]) {
				case "display-user-info":
					displayUserInfo();
					break;
				case "edit-user-info":
					Menu.navigationHistory.add(EditUserInfoMenu.getMenu());
					break;
				case "access-account-list":
					Menu.navigationHistory.add(AccountsMenu.getMenu());
					break;
				default :
					return false;
				}
				return true;
			}
			return false;
		}
		
		return true;
	}
	
	public void displayUserInfo() {
		User u = Application.currentUser;
		System.out.println("Name : " + u.getFirstName() + " " + u.getLastName() + "\n"
				+ "Username : " + u.getUsername() + "\n"
				+ "Phone Number : " + u.getPhoneNumber() +"\n");
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void getHelp() {
		System.out.println(help);
	}
}
