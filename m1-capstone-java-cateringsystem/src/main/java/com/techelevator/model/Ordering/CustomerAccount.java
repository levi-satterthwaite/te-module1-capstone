package com.techelevator.model.Ordering;


public class CustomerAccount {

   private double balance;

   private final int MAX_BALANCE_LIMIT=5000;

    public CustomerAccount() {
        this.balance=0.00;
    }

    public double getBalance() {
        return balance;
    }

    /**
     *  adds
     * @return
     * true is money can be added false otherwise
     */

    public boolean updateBalance(double amount) {
        boolean moneyAdded=false;

       if((amount+balance<=MAX_BALANCE_LIMIT)){
           moneyAdded=true;
           balance+=amount;
       }

       return moneyAdded;

    }








}
