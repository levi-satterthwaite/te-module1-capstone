package com.techelevator.Ordering;

import java.util.Scanner;


public class CustomerAccount {

   protected double balance;

    public double addMoney() {
        balance = 0.00;
        balance+=addMoneyOptions();
        System.out.println("your current balance is "+ "$" + balance);
        return balance;
    }


    public int addMoneyOptions() {
        Scanner in = new Scanner(System.in);
        System.out.println("Add money amount in whole dollar: $ ");
        String userChoice = in.nextLine();
        return Integer.parseInt(userChoice);

    }



}
