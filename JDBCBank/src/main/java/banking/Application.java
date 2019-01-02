package banking;

import banking.Services.BankingService;

public class Application {
	public static void main(String[] args) {
		BankingService bankingService = BankingService.getService();
		System.out.println("Console?");
	}
	
	
	public void initializeMenus() {
		
		}
}
