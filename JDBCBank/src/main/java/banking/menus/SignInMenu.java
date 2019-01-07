package banking.menus;



import java.util.NoSuchElementException;

import banking.Application;
import banking.DAO.BankingDAO;
import banking.Services.BankingService;
import banking.exceptions.ExitingException;
import banking.menus.standard_user.HomeMenu;

public class SignInMenu extends Menu {
	private String commands ="(sign-(in(-admin)?|up))";
	private Integer maxArguments = 3;
	private static String name = "Sign In";
	private static Menu menu = null;
	
	//HELP SCREEN
	private String help= Menu.help + "\n\n" + name.toUpperCase() +" MENU HELPER\n"
			+ "This is the sign in page. You will be taken to this page upon opening the application.\n"
			+ "\nCOMMANDS\n"
			+ "sign-in [username] [password] : attempts to sign in a user with the given username and password.\n"
			+ "sign-in-admin [username] [password] : attempts to sign in a super user with the given username and password.\n"
			+ "sign-up : navigates to the new user menu.\n"
			+ "----------------";
	
	public static Menu getMenu() {
		if(menu == null) {
			menu = new SignInMenu();
		}
		return menu;
	}
	private SignInMenu() {
		
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
				case "sign-in-admin":
					//DAO STUFF
					break;
				case "sign-in":
					//DAO STUFF
					try {
					Application.currentUser = Application.bankingService.signInUser(command.split(" ")[1], command.split(" ")[2]).get();
					} catch(NoSuchElementException e) {
						System.out.println("Couldn't sign in. Please check credentials and try again");
					}
					Menu.navigationHistory.add(HomeMenu.getMenu());
					break;
				case "sign-up":
					Menu.navigationHistory.add(NewUserMenu.getMenu());
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
	
	public String getName() {
		return name;
	}
	
	@Override
	public void getHelp() {
		System.out.println(help);
	}
}
