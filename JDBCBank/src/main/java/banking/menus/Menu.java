package banking.menus;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
	private String commands =  "(sign-out|help|back)";
	private final Integer maxArguments = 1;
	static List<Menu> navigationHistory = new ArrayList<Menu>();
	//Adds the Sign-in Menu as the starting menu
	static {
		navigationHistory.add(SignInMenu.getMenu());
	}
	
	
	/***
	 * @author Leon Wilson
	 * @param command
	 */
	protected boolean parseCommand(String command) /*throws somethingHere*/{
		if(tooManyArguments(command, maxArguments)) {
			//throw too many arguments?
			return false;
		}
		
		if(command.split(" ")[0].matches(commands)) {
			switch(command.split(" ")[0]) {
			case "sign-out":
				//remove all menus but SignInMenu from navigationHistory
				//call the sign out method on the user (DAO?)
				break;
			case "help":
				//call the help method on the last item added to the navigation history
				break;
			case "back":
				//Navigate to the menu right before the current menu
				//remove the last menu item from the navigationHistory
				break;
			default:
				return false;
			}
			return true;
		}
		
		return false;
	}
	
	protected boolean tooManyArguments(String command, Integer maxArguments) {
		String[] split = command.split(" ");
		
		if(split.length > maxArguments) {
			//Too many arguments exception?
			return true;
		}
		return false;
	}
	
	
}
