package com.example.ecommerce;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {

    private static byte[] getSha(String passWord){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(passWord.getBytes(StandardCharsets.UTF_8));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String getEncryptedPassword(String password){
        try {
            BigInteger num=new BigInteger(1,getSha(password));
            StringBuilder result=new StringBuilder(num.toString(16));
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static Customer customerLogin(String userId,String passWord){
        String query="select * from customers where email = '" + userId + "' and password = '" + getEncryptedPassword(passWord) +"'";
        DatabaseConnection dbConn=new DatabaseConnection();

        try {
            ResultSet rs=dbConn.getQueryTable(query);
            if (rs != null && rs.next()) {
               return new Customer(rs.getInt("cid"),rs.getString("name"),rs.getString("email"));

            }
        }catch (Exception e){

            e.printStackTrace();
        }
        return null;
    }

/*
    public static void main(String[] args) {
        System.out.println(customerLogin("parteek@gmail.com","parteek123"));
        System.out.println(getEncryptedPassword("parteek123"));
    }
*/
}
