package banking.menus.standard_user;

import banking.Application;
import banking.exceptions.ExitingException;
import banking.menus.Menu;
import banking.model.*;

public class AccountsMenu extends Menu {
	protected String commands ="(((delete|add|access|display)-account(-list)?)|transfer)";
	private static String name = "Accounts";
	private Integer maxArguments = 2;
	private static Menu menu = null;
	
	//HELP SCREEN
	private String help= Menu.help + "\n\n" + name.toUpperCase() +" MENU HELPER\n"
				+ "This is your Accounts menu. Here you can view all of your accounts and access each individually.\n"
				+ "\nCOMMANDS\n"
				+ "display-account-list : accounts information on the screen.\n"
				+ "delete-account [account_id] : deletes the specified account. (account must have a 0 balance in order to be deleted)\n"
				+ "add-account: goes through the account creation process.\n"
				+ "access-account [account id] : navigates to the requested account's page\n"
				+ "----------------";
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new AccountsMenu();
		}
		return menu;
	}
	
	private AccountsMenu() {
		
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
				//ALL DAO STUFF
				case "display-account-list":
					displayAccountList();
					break;
				case "delete-account":
					break;
				case "add-account":
					break;
				case "access-account":
					for(Account a : Application.currentUser.getAccounts()) {
						if(a.getAccountID().toString().equals(command.split(" ")[1])) {
							Application.currentUser.setAccessedAccount(a);
							break;
						}
					}
					Menu.navigationHistory.add(SingleAccountMenu.getMenu());
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
	
	public void displayAccountList() {
		User u = Application.currentUser;
		for(Account a : u.getAccounts()) {
			System.out.println("\nAccount ID : " + a.getAccountID() + "\n"
					+ "Name : " + a.getAccountName() +"\n");
		}
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
