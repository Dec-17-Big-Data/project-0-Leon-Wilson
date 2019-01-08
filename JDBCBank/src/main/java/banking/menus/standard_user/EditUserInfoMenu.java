package banking.menus.standard_user;

import banking.Application;
import banking.InputHelper;
import banking.exceptions.ExitingException;
import banking.menus.Menu;
import banking.model.SuperUser;
import banking.model.User;

public class EditUserInfoMenu extends Menu {
	protected String commands ="(edit-(((first|last)-name)|password|username|(phone-number)))";
	private static Menu menu = null;
	private Integer maxArguments = 1;
	private static String name = "Edit User";
	
	//HELP SCREEN
	private String help= Menu.help + "\n\n" + name.toUpperCase() +" MENU HELPER\n"
				+ "This is your Edit User menu. Here you can change your user information.\n"
				+ "\nCOMMANDS\n"
				+ "edit-first-name : takes you through the process of changing your first name.\n"
				+ "edit-last-name : takes you through the process of changing your last name.\n"
				+ "edit-password : takes you through the process of changing your password. (will be prompted to confirm password)\n"
				+ "edit-username : takes you through the process of changing your username."
				+ "edit-phone-numner : takes you through the process of changing your phone number.\n"
				+ "----------------";
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new EditUserInfoMenu();
		}
		return menu;
	}
	
	private EditUserInfoMenu() {
		
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
				case "edit-first-name":
					validFirstName();
					break;
				case "edit-last-name":
					validLastName();
					break;
				case "edit-password":
					validPassword();
					break;
				case "edit-username":
					validUsername();
					break;
				case "edit-phone-number":
					validPhoneNumber();
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
	
	public void validFirstName() {
		boolean incomplete = true;
		boolean cancel = false;
		User u = Application.currentUser;
		String input;		
		do {
			System.out.println("Please enter your new first name : ");
			input = InputHelper.getInputHelper().getInput();

			if(input.equals("")) {
				System.out.println("Your name can not be empty");
				cancel = InputHelper.getInputHelper().cancelInput();
				continue;
			}
			
			if(input.toLowerCase().equals(u.getFirstName().toLowerCase()))
			{
				System.out.println("Your first name is already " + input);
				cancel = InputHelper.getInputHelper().cancelInput();
				continue;
			}
			
			do {
				System.out.println("Do you want to change your first name to " + input + "? : Y / N");
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
			if(Application.bankingService.updateFirstName(u.getUserID(), input)) {
				u.setFirstName(input);				
			}
		} else {
			System.out.println("Cancelled first name edit.");
		}
	}
	
	public void validLastName() {
		boolean incomplete = true;
		boolean cancel = false;
		User u = Application.currentUser;
		String input;		
		do {
			System.out.println("Please enter your new last name : ");
			input = InputHelper.getInputHelper().getInput();

			if(input.equals("")) {
				System.out.println("Your last name can not be empty");
				cancel = InputHelper.getInputHelper().cancelInput();
				continue;
			}
			
			if(input.toLowerCase().equals(u.getLastName().toLowerCase()))
			{
				System.out.println("Your last name is already " + input);
				cancel = InputHelper.getInputHelper().cancelInput();
				continue;
			}
			
			do {
				System.out.println("Do you want to change your last name to " + input + "? : Y / N");
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
			if(Application.bankingService.updateLastName(u.getUserID(), input)) {
				u.setLastName(input);				
			}
		} else {
			System.out.println("Cancelled last name edit.");
		}
	}
	
	public void validPassword() {
		User u = Application.currentUser;
		boolean cancel = false;
		boolean incomplete = true;
		String pass = "";
		String passConfirm = "";
		do {
			System.out.println("\nPlease enter your new password.");
			pass = InputHelper.getInputHelper().getInput();
			
			if(pass.length() < 8) {
				System.out.println("Your password is too short");
				continue;
			}
			
			if(pass.equals(u.getPassword()))
			{
				System.out.println("You are already using that password");
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
				System.out.println("Do you want to finalize your password change? : Y / N");
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
			if(Application.bankingService.updatePassword(u.getUserID(), pass)) {
				u.setPassword(pass);
			}
		}else {
			System.out.println("Cancelled password edit.");
		}
	}
	
	public void validPhoneNumber() {
		User u = Application.currentUser;
		boolean cancel = false;
		boolean incomplete = true;
		String phone = "";
		do {
			System.out.println("Please enter your new phone number.");
			phone = InputHelper.getInputHelper().getInput();
			if(phone.equals("")) {
				System.out.println("Phone number can not be empty");
				cancel = InputHelper.getInputHelper().cancelInput();
				continue;
			} else if(!phone.matches("([0-9]{3}-[0-9]{3}-[0-9]{4})")) {
				System.out.println("Invalid phone number entered. Please use the format ###-###-####");
				continue;
			}
			
			do {
				System.out.println("Do you want to change your phone number to " + phone + "? : Y / N");
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
			if(Application.bankingService.updatePhoneNumber(u.getUserID(), phone)){
				u.setPhoneNumber(phone);
			}
		} else {
			System.out.println("Cancelled phone number edit.");
		}
	}
	
	public void validUsername() {
		boolean incomplete = true;
		boolean cancel = false;
		User u = Application.currentUser;
		String input;		
		do {
			System.out.println("Please enter your new username : ");
			input = InputHelper.getInputHelper().getInput();
			
			if(input.equals("")) {
				System.out.println("Your username can not be empty");
				cancel = InputHelper.getInputHelper().cancelInput();
				continue;
			}
			
			if(input.toLowerCase().equals(u.getUsername().toLowerCase()))
			{
				System.out.println("Your username is already " + input);
				cancel = InputHelper.getInputHelper().cancelInput();
				continue;
			}
			
			do {
				System.out.println("Do you want to change your username to " + input + "? : Y / N");
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
			if(Application.bankingService.updateUsername(u.getUserID(), input)) {
				u.setUsername(input);				
			}
		} else {
			System.out.println("Cancelled username edit.");
		}
	}
}

