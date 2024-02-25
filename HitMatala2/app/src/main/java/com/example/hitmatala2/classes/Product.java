package com.example.hitmatala2.classes;

public class Product {
    private String name;
    private String amount;

    public Product(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount){
        this.amount = amount;
    }
}
