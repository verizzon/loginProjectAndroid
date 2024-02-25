package com.example.hitmatala2.classes;

import com.example.hitmatala2.fragments.FragmentOne;

import java.util.ArrayList;
import com.example.hitmatala2.classes.Product;
public class User {
    private String username;
    private String password;
    private ArrayList<Product> productList;



    public User(String username, String password) {
        this.username = username;
        this.password = password;
        productList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public ArrayList<Product> getProductList() {
        return productList;
    }
}
