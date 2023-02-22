package com.example.ecommerce;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SignUp {
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

    public static boolean CustomerSignUp(String Username,String password,String email){
        String query="insert into customers (name,email,password) values ('" +Username+"','"+email+"','"+getEncryptedPassword(password)+"')";
        DatabaseConnection dbConn=new DatabaseConnection();
        try {
            return dbConn.insertUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
