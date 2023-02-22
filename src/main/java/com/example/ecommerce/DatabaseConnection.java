package com.example.ecommerce;
import java.sql.*;

public class DatabaseConnection {

    String dbURL = "jdbc:mysql://localhost:3306/ecom";
    String userName = "root";
    String passWord= "parteek@barca";

    private Statement getStatement(){

        try{
            Connection conn=DriverManager.getConnection(dbURL,userName,passWord);
            return conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getQueryTable(String query){
        Statement statement=getStatement();
        try{
            return statement.executeQuery(query);
        }catch (SQLException e){

            e.printStackTrace();
        }

        return null;
    }

    public boolean insertUpdate(String query){
        Statement statement=getStatement();
        try{
            statement.executeUpdate(query);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

/*
    public static void main(String[] args) {
        String query = "select * from products";
        DatabaseConnection dbconn=new DatabaseConnection();
        ResultSet rs=dbconn.getQueryTable(query);
        if(rs!=null){
            System.out.println("Connected to database");
        }else{
            System.out.println("Fail");
        }


    }
*/
}
