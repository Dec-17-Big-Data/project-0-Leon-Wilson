package banking.menus.standard_user;

import banking.Application;
import banking.exceptions.ExitingException;
import banking.exceptions.InvalidValueException;
import banking.exceptions.account_exceptions.OverdraftException;
import banking.menus.Menu;
import banking.model.Account;

public class SingleAccountMenu extends Menu {
	protected String commands ="((display-(account-summary|(all|pending|posted)?-transactions|balance)|withdraw|deposit))";
	private static String name = "Individual Account";
	private Integer maxArguments = 2;
	private static Menu menu = null;
	
	//HELP SCREEN
	private String help= Menu.help + "\n\n" + name.toUpperCase() +" MENU HELPER\n"
				+ "This is your Individual Account menu. Here you can view the accounts details as well deposit or withdrawl money\n"
				+ "\nCOMMANDS\n"
				+ "display-account-summary : displays accounts information on the screen.\n"
				+ "withdraw [amount]       : withdraws the specified amount from the account. (can lead to an overdraft) \n"
				+ "deposit [amount]        : deposits the specified amount to the account.\n"
				+ "display-balance         : displays the balance for the current account\n"
				+ "----------------";
	public static Menu getMenu() {
		if(menu == null) {
			menu = new SingleAccountMenu();
		}
		return menu;
	}
	
	private SingleAccountMenu() {
		
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
				case "display-account-summary":
					displayAccountSummary();
					break;
				case "withdraw":
					try {
						if(!withdraw(Double.valueOf(command.split(" ")[1]))) {
							System.out.println("\nWithdrawl failed.");
							return false;
						} else {
							System.out.println("\nWithdrawl successful");
						}
					} catch(IndexOutOfBoundsException e) {
						System.out.println("\nNot enough arguments provided");
						return false;
					}
					break;
				case "deposit":
					try {
						if(!deposit(Double.valueOf(command.split(" ")[1]))) {
							System.out.println("\nDeposit failed.");
							return false;
						} else {
							System.out.println("\nDeposit successful");
						}
					} catch(IndexOutOfBoundsException e) {
						System.out.println("\nNot enough arguments provided");
						return false;
					}
					break;
				case "display-balance":
					displayBalance();
					break;
				default :
					System.out.println("\nSyntax error.");
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
	
	public boolean withdraw(Double amount) {
		try {
			Account a = Application.currentUser.getAccessedAccount();
			if(amount < 0) {
				throw new InvalidValueException("Invalid value entered.");
			}
			if(a.getBalance() - amount < 0) {
				throw new OverdraftException("Account would overdraft.");
			}
			
			a.setBalance(a.getBalance() - amount);
			//UPDATE IN DATABASE
			if(Application.bankingService.updateBalance(a.getAccountID(), a.getBalance())) {
				return true;
			} else {
				System.out.println("\nFailed while trying to update balance.");
			}
			return false;
		} catch (OverdraftException e) {
			System.out.println("\nNot enough money in account to withdraw " + amount);
		} catch (InvalidValueException e) {
			System.out.println("\nThe value given was invalid. Please try again");
		}
		return false;
	}
	
	public boolean deposit(Double amount) {
		try {
			Account a = Application.currentUser.getAccessedAccount();
			if(amount < 0) {
				throw new InvalidValueException("Invalid value entered.");
			}
			
			a.setBalance(a.getBalance() + amount);
			//UPDATE IN DATABASE
			if(Application.bankingService.updateBalance(a.getAccountID(), a.getBalance())) {
				return true;
			}else {
				System.out.println("\nFailed while trying to update balance.");
			}
			return false;
		} catch (InvalidValueException e) {
			System.out.println("\nThe value given was invalid. Please try again");
		}
		return false;
	}
	
	public void displayAccountSummary() {
		Account a = Application.currentUser.getAccessedAccount();
		System.out.println("\nACCOUNT : "+a.getAccountID() + "\n"
				+"NAME : "+ a.getAccountName() + "\n"
				+"TYPE : "+ a.getType() + "\n"
				+"BALANCE : "+ a.getBalance() + "\n");
	}
	
	public void displayBalance() {
		Account a = Application.currentUser.getAccessedAccount();
		System.out.println("\nThe current balance of the account is " + a.getBalance());
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
