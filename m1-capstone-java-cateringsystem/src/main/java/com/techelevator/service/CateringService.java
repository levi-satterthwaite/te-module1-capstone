package com.techelevator.service;

import com.techelevator.model.catering.Product;
import com.techelevator.PromptFileReader;
import com.techelevator.model.catering.ProductShelf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CateringService {

    private Map<String, ProductShelf> productInventory;


    public CateringService() {
        productInventory = new HashMap<>();
        userFilePath();
    }


    public Map<String, ProductShelf> getProductInventory() {
        return this.productInventory;
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
            ProductShelf productShelf = new ProductShelf(product, product.INITIAL_PRODUCT_INVENTORY);

            productInventory.put(lineArr[0], productShelf);

        }

    }


    //function requires the productCode and the amount to be correct
    public void removeFromShelf(String productCode, int amount) {

        ProductShelf productShelf = productInventory.get(productCode);

        productShelf.setAmount(productShelf.getAmount() - amount);
        productInventory.put(productCode, productShelf);
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
        for (String productCode : productInventory.keySet()) {

            ProductShelf productShelf = productInventory.get(productCode);
            String amountRemainingStr = (productShelf.getAmount() == 0) ? "SOLD OUT" : String.valueOf(productShelf.getAmount());
            Product product = productShelf.getProduct();


            System.out.printf("%-15s %-23s $%-15.2f %-15s\n",
                    product.getProductCode(),
                    product.getProductName(),
                    product.getProductPrice(),
                    amountRemainingStr);

        }

    }
}
