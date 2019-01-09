package banking.menus.super_user;

import banking.Application;
import banking.InputHelper;
import banking.exceptions.ExitingException;
import banking.menus.Menu;
import banking.model.SuperUser;

public class EditSuperUserInfoMenu extends Menu {
	protected String commands ="edit-super-(username|password)";
	private Integer maxArguments = 1;
	private static String name = "Edit Super User";
	private static Menu menu = null;
	
	//HELP SCREEN
	private String help= Menu.help + "\n\n" + name.toUpperCase() +" MENU HELPER\n"
			+ "This is the Super User page. Here you will have administrative priviledges over the bank users.\n"
			+ "\nCOMMANDS\n"
			+ "edit-super-username : change the current super user's username\n"
			+ "edit-super-password : change the current super user's password\n"
			+ "----------------";
	public static Menu getMenu() {
		if(menu == null) {
			menu = new EditSuperUserInfoMenu();
		}
		return menu;
	}
	
	private EditSuperUserInfoMenu() {
		
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
				case "edit-super-password":
					if(!changePassword()) {
						System.out.println("\nPassword not changed.");
						return false;
					} else {
						System.out.println("\nPassword was changed.");
					}
					break;
				case "edit-super-username":
					if(!changeUsername()) {
						System.out.println("\nUsername not changed.");
						return false;
					} else {
						System.out.println("\nUsername was changed.");
					}
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
	
	public boolean changePassword() {
		SuperUser u = Application.currentSuperUser;
		boolean cancel = false;
		boolean incomplete = true;
		String pass = "";
		String passConfirm = "";
		do {
			System.out.println("\nPlease enter your new password.");
			pass = InputHelper.getInputHelper().getInput();
			
			if(pass.length() < 8) {
				System.out.println("\nYour password is too short");
				continue;
			}
			
			if(pass.equals(u.getPassword()))
			{
				System.out.println("\nYou are already using that password");
				cancel = InputHelper.getInputHelper().cancelInput();
				continue;
			}
			
			System.out.println("\nPlease confirm your password.");
			passConfirm = InputHelper.getInputHelper().getInput();
			
			if(!pass.equals(passConfirm)){
				System.out.println("\nPasswords do not match");
				continue;
			}
			do {
				System.out.println("\nDo you want to finalize your password change? : Y / N");
				String confirm = InputHelper.getInputHelper().getInput();
				if(confirm.toUpperCase().equals("Y")) {
					incomplete = false;
					break;
				} else if(confirm.toUpperCase().equals("N")) {
					incomplete = true;
					cancel = InputHelper.getInputHelper().cancelInput();
					break;
				}
			}while(true);
		}while(incomplete && !cancel);
		
		if(!cancel) {
			if(Application.bankingService.updateSuperPassword(u.getUserID(), pass)) {
				u.setPassword(pass);
				return true;
			} else {
				System.out.println("\nFailed while trying to update password.");
				return false;
			}
		}else {
			System.out.println("\nCancelled password edit.");
			return false;
		}
	}
	
	public boolean changeUsername() {
		boolean incomplete = true;
		boolean cancel = false;
		SuperUser u = Application.currentSuperUser;
		String input;		
		do {
			System.out.println("\nPlease enter your new username : ");
			input = InputHelper.getInputHelper().getInput();

			if(input.equals("")) {
				System.out.println("\nYour username can not be empty");
				cancel = InputHelper.getInputHelper().cancelInput();
				continue;
			}
			
			if(input.toLowerCase().equals(u.getUsername().toLowerCase()))
			{
				System.out.println("\nYour username is already " + input);
				cancel = InputHelper.getInputHelper().cancelInput();
				continue;
			}
			
			do {
				System.out.println("\nDo you want to change your username to " + input + "? : Y / N");
				String confirm = InputHelper.getInputHelper().getInput();
				if(confirm.toUpperCase().equals("Y")) {
					incomplete = false;
					break;
				} else if(confirm.toUpperCase().equals("N")) {
					incomplete = true;
					cancel = InputHelper.getInputHelper().cancelInput();
					break;
				}
			}while(true);
		}while (incomplete && !cancel);
		if(!cancel) {
			if(Application.bankingService.updateSuperUsername(u.getUserID(), input)) {
				u.setUsername(input);
				return true;
			} else {
				System.out.println("\nFailed while trying to update username.");
				return false;
			}
		} else {
			System.out.println("\nCancelled username edit.");
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
