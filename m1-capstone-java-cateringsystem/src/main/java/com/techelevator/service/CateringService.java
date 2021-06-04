package com.techelevator.service;

import com.techelevator.Product;
import com.techelevator.PromptFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CateringService {

    private Map<Product, Integer> productInventory;


    public CateringService() {
        productInventory = new HashMap<>();
        userFilePath();
    }


    /**
     * prompts the user for a correct file path containing the items
     */
    private void userFilePath() {

        Scanner in = new Scanner(System.in);

        System.out.println("Provide file location containing inventory Items.");

        PromptFileReader fileReader = new PromptFileReader(in.nextLine());

        //system exception, deal with it here

        try {
            mapFileToItems(fileReader.getFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    /**
     * a method that uses a file and created a map of type <Product,Integer>
     *
     * @param file pipe delimited file containing the inventory items
     */

    private void mapFileToItems(File file) throws FileNotFoundException {


        Scanner scanner = new Scanner(file);


        while (scanner.hasNext()) {
            String[] lineArr = scanner.nextLine().split("\\|");

            Product product = new Product(lineArr[0], lineArr[1], Double.parseDouble(lineArr[2]), lineArr[3]);
            productInventory.put(product, product.INITIAL_PRODUCT_INVENTORY);

        }

    }


    public void inventoryReport() {
        double totalPayment = 0;

        System.out.printf("%-15s %-23s %-15s %-15s\n",
                "Product Code",
                "Product Name",
                "Purchase Price",
                "Amount Remaining"
        );

        System.out.println("--------------------------------------------------------------------------------------");
        for (Product product : productInventory.keySet()) {

            Integer amountRemaining= productInventory.get(product);
            String amountRemainingStr = (amountRemaining ==0) ? "SOLD OUT" :  amountRemaining.toString();




            System.out.printf("%-15s %-23s $%-15.2f %-15s\n",
                    product.getProductCode(),
                    product.getProductName(),
                    product.getProductPrice(),
                    amountRemainingStr);

        }

    }
}
