package com.techelevator.AuditSystem;

import com.techelevator.model.Ordering.CustomerAccount;
import com.techelevator.service.CateringService;
import com.techelevator.service.OrderingService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Audit {

    // 01/01/2019 12:00:00 PM ADD MONEY: $500.00 $500.00
    /*
    -need timeStamp for: when adding money, adding products
     */
    private CustomerAccount customerAccount;
    private OrderingService orderingService;

    public Audit() {
        customerAccount = new CustomerAccount();
        orderingService = new OrderingService(forNow);
    }

    CateringService forNow = new CateringService();

    String pathAuditLog = "C:\\Users\\Student\\source\\repos\\team0-java-blue-week04-pair-exercises\\m1-capstone-java-cateringsystem\\src\\main\\java\\com\\techelevator\\AuditSystem\\Log.txt";
    File fileToWriteTo = new File(pathAuditLog);

    public void getTime () {
       String addItemsTimeStamp = customerAccount.timeStamp();

       String addMoneyTimeStamp = orderingService.timeStamp();







    }







}
