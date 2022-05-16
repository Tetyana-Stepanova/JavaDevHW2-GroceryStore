package model;

import lombok.Data;

@Data
public class Product {
    private String productName;
    private double price;
    private int promAmount;
    private double promPrice;

    public Product(String productName, double price, int promAmount, double promPrice){
        this.productName = productName;
        this.price = price;
        this.promAmount = promAmount;
        this.promPrice = promPrice;
    }

    public String toString() {
        return productName + "," + price + "," + promAmount + "," + promPrice;
    }
}