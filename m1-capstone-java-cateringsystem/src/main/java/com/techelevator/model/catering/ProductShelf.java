package com.techelevator.model.catering;

import com.techelevator.model.catering.Product;

public class ProductShelf {

    private Product product;
    private int amount;

    public ProductShelf(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
