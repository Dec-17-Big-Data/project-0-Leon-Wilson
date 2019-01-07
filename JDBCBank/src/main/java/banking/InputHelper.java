package banking;

import java.util.Scanner;

public class InputHelper {
	private static InputHelper inputHelper = null;
	private Scanner scanner = null;
	
	private InputHelper() {
		scanner = new Scanner(System.in);
	}
	
	public static InputHelper getInputHelper() {
		if(inputHelper == null) {
			inputHelper = new InputHelper();
		}
		return inputHelper;
	}
	
	public Scanner getScanner() {
		return scanner;
	}
	
	public String getInput() {
		return scanner.nextLine();
	}
	
	public boolean cancelInput() {
		do {
			System.out.println("Would you like to cancel? Y / N");
			String conf2 = getInput();
			if(conf2.toUpperCase().equals("Y")) {
				return true;
			} else if(conf2.toUpperCase().equals("N")) {
				return false;
			}
		} while(true);
	}
	
	public void closeScanner() {
		if(scanner != null) {
			scanner.close();
		}
	}
}
