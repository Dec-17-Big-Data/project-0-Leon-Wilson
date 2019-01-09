package banking;

import java.util.List;
import java.util.NoSuchElementException;

import banking.Services.BankingService;
import banking.exceptions.ExitingException;
import banking.menus.Menu;
import banking.model.*;

public class Application {
	public static User currentUser;
	public static SuperUser currentSuperUser = null;
	public static BankingService bankingService;
	public static void main(String[] args) {
		bankingService = BankingService.getService();
		InputHelper helper = InputHelper.getInputHelper();
		Boolean exit = false;
		
		
		while(!exit) {
			Menu.navigationHistory.get(Menu.navigationHistory.size()-1).getTrail();
			if(currentSuperUser != null) {
				System.out.println("super user " + currentSuperUser.getUsername() + " is logged in.");
			}
			if(currentUser != null) {
				System.out.println("current user : " + currentUser.getUsername());
			} else {
				System.out.println("Please sign in using the 'sign-in' command. (type help for more details)");
			}
			System.out.println("currently on : " + Menu.navigationHistory.get(Menu.navigationHistory.size()-1).getName() + " Menu");
			String input = helper.getInput();
			try {
				if(Menu.navigationHistory.get(Menu.navigationHistory.size()-1).parseCommand(input))
				{
					
				}
			} catch (ExitingException e) {
				// TODO Auto-generated catch block
				exit = true;
			}
			
		}
		helper.closeScanner();
	}
	
	public List<Menu> getMenus() {
		return Menu.navigationHistory;
	}
	
	public Menu getCurrentMenu() {
		return Menu.navigationHistory.get(Menu.navigationHistory.size()-1);
	}
	
	public void addMenu(Menu menu) {
		Menu.navigationHistory.add(menu);
	}
}
