package com.example.ecommerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;


    public int getId(){

        return id.get();
    }

    public String getName(){
        return name.get();
    }

    public Double getPrice(){
        return price.get();
    }

    Product(int id,String name,Double price){
        this.id=new SimpleIntegerProperty(id);
        this.name=new SimpleStringProperty(name);
        this.price=new SimpleDoubleProperty(price);
    }

    public static ObservableList<Product> getAllProducts(){
        String allProductList="select * from products";
        return getProduct(allProductList);
    }
    public static ObservableList<Product> getProduct(String query){

        DatabaseConnection dbConn=new DatabaseConnection();
        ObservableList<Product> result= FXCollections.observableArrayList();
        try {
            ResultSet rs = dbConn.getQueryTable(query);
            if(rs!=null){
                while(rs.next()){
                result.add(new Product(rs.getInt("pid"),rs.getString("name"),rs.getDouble("price")));
            }
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
