package com.example.ecommerce;

import javafx.scene.chart.PieChart;

import java.sql.ResultSet;

public class Order {

    public boolean placeOrder(Customer cust,Product prod){

        try{
            DatabaseConnection dbConn=new DatabaseConnection();
            String query="insert into orders (customer_id,product_id,status) values ("+cust.getId()+","+prod.getId() +", 'Ordered')";

            return dbConn.insertUpdate(query);
        }catch (Exception e){

            e.printStackTrace();
        }
        return false;
    }
}
