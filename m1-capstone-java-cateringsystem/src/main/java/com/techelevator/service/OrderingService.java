package com.techelevator.service;


import com.techelevator.model.Ordering.Cart;
import com.techelevator.model.Ordering.CustomerAccount;
import com.techelevator.model.catering.Product;
import com.techelevator.model.catering.ProductShelf;

import java.util.Map;

public class OrderingService {

    private final CustomerAccount customerAccount;

   private final CateringService cateringService;

    private final Cart cart;


    public OrderingService(CateringService cateringService) {
        this.cateringService = cateringService;
        customerAccount= new CustomerAccount();
        cart= new Cart();

    }

    /**
     * orders a product from inventory. returns an
     * @return
     *  {a message indication the status of order}
     */
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

    }
    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }


}
