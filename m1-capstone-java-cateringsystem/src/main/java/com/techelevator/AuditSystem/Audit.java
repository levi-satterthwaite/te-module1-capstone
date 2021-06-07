package com.techelevator.AuditSystem;

import com.techelevator.model.ordering.Cart;
import com.techelevator.model.ordering.CustomerAccount;
import com.techelevator.service.CateringService;
import com.techelevator.service.OrderingService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

public class Audit {

   List <String> logs;

    public Audit() {
       this.logs= new ArrayList<>();
    }


    public void addToLog(String message) {

        String addToCartTimestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(System.currentTimeMillis());


        logs.add(addToCartTimestamp+ " "+ message);

    }



    public void writeLogToFile(){
        String pathToWrite= "Log.txt";

        File fileToWrite= new File(pathToWrite);
        try (PrintWriter printWriter = new PrintWriter(fileToWrite)){
            for(String log: logs){
                printWriter.println(log);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }



}
