package com.techelevator.view;

import java.util.Scanner;

/*
 * This is the only class that should have any usage of System.out or System.in
 */
public class Menu {

	public void showWelcomeMessage() {
		System.out.println("*************************");
		System.out.println("**     Weyland Corp.   **");
		System.out.println("**      Catering       **");
		System.out.println("*************************");
		System.out.println();
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("(1) Display Catering Items");
		System.out.println("(2) Order");
		System.out.println("(3) Quit");
	}
}
