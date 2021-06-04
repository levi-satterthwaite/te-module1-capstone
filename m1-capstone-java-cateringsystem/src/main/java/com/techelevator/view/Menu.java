package com.techelevator.view;

import com.techelevator.PromptFileReader;


import java.util.Scanner;

/*
 * This is the only class that should have any usage of System.out or System.in
 */
public class Menu {
	
	private static final Scanner in = new Scanner(System.in);

	public void showWelcomeMessage() {
		System.out.println("*************************");
		System.out.println("**     Weyland Corp.   **");
		System.out.println("**      Catering       **");
		System.out.println("*************************");
		System.out.println();
	}


	public int userChoice() {
		System.out.println();
		System.out.println("(1) Display Catering Items");
		System.out.println("(2) Order");
		System.out.println("(3) Quit");
		String userChoice = in.nextLine();
		return Integer.parseInt(userChoice);

	}

	public int purchasingMenu() {
		System.out.println();
		System.out.println("(1) Add Money");
		System.out.println("(2) Select Products");
		System.out.println("(3) Complete Transaction");
		String userChoice = in.nextLine();
		return Integer.parseInt(userChoice);

	}
}
