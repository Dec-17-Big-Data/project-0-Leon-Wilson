package banking;

import java.util.List;
import java.util.NoSuchElementException;

import banking.Services.BankingService;
import banking.exceptions.ExitingException;
import banking.menus.Menu;
import banking.model.User;

public class Application {
	public static void main(String[] args) {
		BankingService bankingService = BankingService.getService();
		InputHelper helper = InputHelper.getInputHelper();
		Boolean exit = false;
		List<User> ut = null;
		try {
		ut = bankingService.getAllUsers().get();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		if(ut != null) {
			for(User u : ut) {
				System.out.println(u.getUsername());
			}
		}
		
		while(!exit) {
			System.out.println("\ncurrently on : " + Menu.navigationHistory.get(Menu.navigationHistory.size()-1).getName() + " Menu");
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
	
	
	public void initializeMenus() {
		
		}
}
