package com.techelevator.service;


import com.techelevator.model.Ordering.Cart;
import com.techelevator.model.Ordering.CustomerAccount;
import com.techelevator.model.catering.Product;
import com.techelevator.model.catering.ProductShelf;

import java.util.Map;

public class OrderingService {

    CustomerAccount customerAccount;

    CateringService cateringService;

    Cart cart;


    public OrderingService(CateringService cateringService) {
        this.cateringService = cateringService;
        customerAccount= new CustomerAccount();
        Cart cart= new Cart();


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
        int amountInStock= inventory.get(productCode).getAmount();
        if(amountInStock==0){
            return "SOLD OUT";
        }
        if(amountInStock<amount){
            return "Insufficient Stock!";
        }

        //there must be enough
        cateringService.removeFromShelf(productCode,amount);

        cart.addToCart(productCode,amount);

        return "Successfully added to cart!";





    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }


}
