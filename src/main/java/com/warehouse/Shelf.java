package com.warehouse;

import com.warehouse.Item.ProductEnum;

public class Shelf {

    private ProductEnum product;
    private int numberOfProduct;

    public Shelf() {
        this.product = null;
        this.numberOfProduct = 0;
    }

    public boolean isEmpty() {
        return product == null;
    }

    public void addProduct(ProductEnum product) {
        throwIfProductTypeIsNotValid(product);
        this.product = product;
        this.numberOfProduct++;
    }

    public void addMultipleProduct(ProductEnum product, int numberOfProduct) {
        throwIfProductTypeIsNotValid(product);
        this.product = product;
        this.numberOfProduct += numberOfProduct;
    }

    private void throwIfProductTypeIsNotValid(ProductEnum product) {
        if (this.product != null && this.product != product) {
            throw new IllegalArgumentException("Shelf can only have one type of product");
        }
    }
    public void removeProduct(int numberOfProduct) {

        if (this.numberOfProduct - numberOfProduct < 0) {
            throw new IllegalStateException("Shelf does not have enough product to remove");
        }

        this.numberOfProduct -= numberOfProduct;

        if (this.numberOfProduct == 0) {
            this.product = null;
        }
    }

    public ProductEnum getProduct() {
        return product;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }
}
