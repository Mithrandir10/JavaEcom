package com.example.ecommerce;

public class Customer {

    int id;
    String name;
    String email;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
