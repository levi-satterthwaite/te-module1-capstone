package com.techelevator.service;


import com.techelevator.model.Ordering.Cart;
import com.techelevator.model.Ordering.CustomerAccount;
import com.techelevator.model.catering.Product;
import com.techelevator.model.catering.ProductShelf;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderingService {
    /*
    Function of this class:
    Access CustomerAccount
     -decide if user can purchase

     Access CateringService
     -Manage if the product is available in catering inventory


     Access Cart




     */


    private final CustomerAccount customerAccount;

   private final CateringService cateringService;

    private final Cart cart;


    public OrderingService(CateringService cateringService) {
        this.cateringService = cateringService;
        customerAccount= new CustomerAccount();
        cart= new Cart();

    }


    public String order(String productCode,int amount){
        Map<String,ProductShelf> inventory= cateringService.getProductInventory();

        if(!inventory.containsKey(productCode)){
            return "Product NOT FOUND in inventory!";
        }

        Product product= inventory.get(productCode).getProduct();
        int amountInStock= inventory.get(productCode).getNumOfProducts();
        if(amountInStock==0){
            return "SOLD OUT!";
        }
        if(amountInStock<amount){
            return "Insufficient Stock!";
        }

        double requiredBalanceForPurchase= product.getProductPrice()* amount;

        if(customerAccount.getBalance()<requiredBalanceForPurchase){
            return "NOT ENOUGH FUND available for purchase! Please add more money to balance.";
        }

        customerAccount.updateBalance(-1* requiredBalanceForPurchase);


        //there must be enough
        cateringService.removeFromShelf(productCode,amount);

        cart.addToCart(productCode,amount);

        return "Successfully added to cart! :)";

    }

    public void completeTransaction(){
        Map<String,Integer> cartItems= cart.getCartItems();
        double totalAmount = 0;

        for (String productCode : cartItems.keySet()) {

            Product product = cateringService.getProductInventory().get(productCode).getProduct();

            int numOfProduct = cartItems.get(productCode);
            String productType = product.toString();
            String productName = product.getProductName();
            Double productPrice = product.getProductPrice();
            Double tolCostPerProduct = numOfProduct * productPrice;

            System.out.printf("%-10s %-15s %-23s %-15.2f %-15.2f\n",
                    numOfProduct,
                    productType,
                    productName,
                    productPrice,
                    tolCostPerProduct);


            totalAmount+=tolCostPerProduct;


        }
        System.out.println("Total: " + totalAmount);


        //give back the user his change
        System.out.println("\n");

        final Double[] BILLS = { 50.00, 20.00, 10.00, 100.00, 5.00, 1.00};
        final Double [] COINS={0.5,0.25,0.1,0.05,0.01};

        double customerChangeDollar = (int) customerAccount.getBalance();
        double customerChangeCent = (customerAccount.getBalance() - customerChangeDollar);

        Map<Double,Integer> dollarChange = getChange(BILLS,customerChangeDollar);
        Map<Double,Integer> centChange =  getChange(COINS,customerChangeCent);

        for (Double totalDollarNotes : dollarChange)


        System.out.println("Your change is " + dollarChange + " dollars and " + centChange + " cents");


        //make user balance go to zero
        customerAccount.updateBalance(-1* customerAccount.getBalance());

    }
    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    //giving the smallest amount of change


    private Map<Double,Integer> getChange(Double [] changeDrawer, Double moneyToChange){


        //need to sort the change drawer in dec. order
        Arrays.sort(changeDrawer, Collections.reverseOrder());

        //initialize our Map that will contain the changes and their count
        Map<Double,Integer> changeToGive= new HashMap<>();

        for(int i=0;i<changeDrawer.length;i++){
            if(changeDrawer[i]<=moneyToChange){

                 int billCount=0;

                if(changeToGive.containsKey(changeDrawer[i])){
                    billCount= changeToGive.get(changeDrawer[i]);
                }
                changeToGive.put(changeDrawer[i], billCount+1);
                moneyToChange=moneyToChange-changeDrawer[i];
                if(moneyToChange>=changeDrawer[i]){
                    i--;
                }

            }

        }
        return changeToGive;

    }





}
