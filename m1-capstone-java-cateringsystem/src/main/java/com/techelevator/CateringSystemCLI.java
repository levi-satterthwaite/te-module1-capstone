package com.techelevator;

import com.techelevator.Ordering.CustomerAccount;
import com.techelevator.service.CateringService;
import com.techelevator.view.Menu;

/*
 * This class should control the workflow of the application, but not do any other work
 * 
 * The menu class should communicate with the user, but do no other work
 * 
 * The work of the Catering System should be in other classes that you build and 
 * call from here. 
 */
public class CateringSystemCLI {

	/*
	 * The menu class is instantiated in the main() method at the bottom of this file.  
	 * It is the only class instantiated in the starter code.  
	 * You will need to instantiate all other classes using the new keyword before you can use them.
	 * 
	 * Remember every class and data structure is a data types and can be passed as arguments to methods or constructors.
	 */
	private Menu menu;
	private CateringService cateringService;
	private CustomerAccount customerAccount;


	public CateringSystemCLI(Menu menu) {
		this.menu = menu;
	}

	/*
	 * Your application starts here
	 */
	public void run() {


		//show welcome message
		menu.showWelcomeMessage();



		//initialize catering service. catering service will ask for a file
		cateringService= new CateringService();


		while (true) {
			/*
			Display the Starting Menu and get the users choice.
			Remember all uses of System.out and System.in should be in the menu

			
			IF the User Choice is Display Vending Machine Items, 
				THEN display vending machine items
			ELSE IF the User's Choice is Purchase,
				THEN go to the purchase menu
			*/

			int userChoice= menu.userChoice();

			if(userChoice==1){
				cateringService.inventoryReport();

			}else if(userChoice ==2){
				menu.purchasingMenu();

			}else if(userChoice ==3){
				break;
			}

		}
		customerAccount = new CustomerAccount();
		customerAccount.addMoney();
	}

	/*
	 * This starts the application, but we shouldn't need to change it.
	 */
	public static void main(String[] args) {
		Menu menu = new Menu();
		CateringSystemCLI cli = new CateringSystemCLI(menu);
		cli.run();
	}
}
