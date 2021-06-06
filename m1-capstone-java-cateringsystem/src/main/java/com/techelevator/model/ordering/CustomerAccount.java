package com.techelevator.model.ordering;


import com.techelevator.AuditSystem.TimeStamp;

import java.text.SimpleDateFormat;

public class CustomerAccount implements TimeStamp {

   private double balance;

   private final int MAX_BALANCE_LIMIT=5000;
   protected String addMoneyTimeStamp;

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
           addMoneyTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(System.currentTimeMillis());
       }

       return moneyAdded;

    }


    @Override
    public String timeStamp() {
        return addMoneyTimeStamp;
    }
}
