package banking.menus.super_user;

import banking.Application;
import banking.InputHelper;
import banking.exceptions.ExitingException;
import banking.exceptions.InvalidValueException;
import banking.menus.Menu;
import banking.model.Account;
import banking.model.AccountTypes;
import banking.model.SuperUser;
import banking.model.User;

public class SuperUserHomeMenu extends Menu {
	protected String commands ="(((edit|create)-super-user)|access-user-list)";
	private Integer maxArguments = 1;
	private static String name = "Super User";
	private static Menu menu = null;
	
	//HELP SCREEN
	private String help= Menu.help + "\n\n" + name.toUpperCase() +" MENU HELPER\n"
			+ "This is the Super User Home page. Here you will have administrative priviledges over the bank users.\n"
			+ "\nCOMMANDS\n"
			+ "create-super-user : creates a new super user.\n"
			+ "edit-super-user   : navigates to the super user edit menu\n"
			+ "access-user-list  : navigates to the user list.\n"
			+ "----------------";
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new SuperUserHomeMenu();
		}
		return menu;
	}
	
	private SuperUserHomeMenu() {
		
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
				case "create-super-user":
					if(!createSuperUser()) {
						System.out.println("\nNew super user not created.");
					} else {
						System.out.println("\nNew super user created.");
					}
					break;
				case "edit-super-user":
					Menu.navigationHistory.add(EditSuperUserInfoMenu.getMenu());
					break;
				case "access-user-list":
					Menu.navigationHistory.add(UsersMenu.getMenu());
					break;
				default :
					return false;
				}
				return true;
			} else {
				System.out.println("\nUnknown command");
			}
			return false;
		}
		return true;
	}
	
	public boolean createSuperUser() {
		boolean incomplete = true, cancelled = false;
		SuperUser u = Application.currentSuperUser;
		String username = "";
		String pass = "";
		String passConfirm = "";
		
		
		do {
			//USERNAME
			do {
				if(cancelled) break;
				System.out.println("Please enter your username");
				boolean name_found = false;
				String input = InputHelper.getInputHelper().getInput();
				
				if(input.equals(""))
				{
					System.out.println("username cannot be empty");
					cancelled = InputHelper.getInputHelper().cancelInput();
					continue;
				}
				
				if(!Application.bankingService.checkSuperUsernameAvailability(username)) {
					System.out.println("Username already exist");
					cancelled = InputHelper.getInputHelper().cancelInput();
					continue;
				}
				
				username = input;
				break;
			}while(true);
			
			//PASSWORD
			do {
				if(cancelled) break;
				System.out.println("\nPlease enter password.");
				pass = InputHelper.getInputHelper().getInput();
				
				if(pass.length() < 8) {
					System.out.println("Your password is too short");
					continue;
				}
				
				System.out.println("\nPlease confirm your password.");
				passConfirm = InputHelper.getInputHelper().getInput();
				
				if(!pass.equals(passConfirm)){
					System.out.println("\nPasswords do not match");
					cancelled = InputHelper.getInputHelper().cancelInput();
					continue;
				}
				
				break;
			} while(true);
			
			incomplete = false;
		}while(incomplete && !cancelled);
		
		if(!cancelled) {
			if(Application.bankingService.addSuperUser(username, pass)) {
				return true;
			} else {
				System.out.println("\nFailed while trying to create super user.");
				return false;
			}
		} else {
			System.out.println("\nCancelled super user creation.");
			return false;
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void getHelp() {
		// TODO Auto-generated method stub
		System.out.println(help);
	}
}
