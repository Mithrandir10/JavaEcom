package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;

public class Order {
     
    TableView<Product> orderTable;
    public static boolean placeOrder(Customer cust,Product prod){

        try{
            DatabaseConnection dbConn=new DatabaseConnection();
            String query="insert into orders (customer_id,product_id,status) values ("+cust.getId()+","+prod.getId() +", 'Ordered')";

            return dbConn.insertUpdate(query);
        }catch (Exception e){

            e.printStackTrace();
        }
        return false;
    }

    public static int placeMultiOrder(ObservableList<Product> productList,Customer customer){
        int count=0;
        try{
            for(Product prod : productList){
                if(placeOrder(customer,prod))count++;
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public Pane getAllProducts(){



        ObservableList<Product> productList=Product.getAllProducts();

        return createTableFromList(productList);
    }

    public Pane createTableFromList(ObservableList<Product> orderList){
        TableColumn id=new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name=new TableColumn("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price =new TableColumn("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));



        orderTable=new TableView<>();
        orderTable.setItems(orderList);
        orderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        orderTable.getColumns().addAll(id,name,price);

        Pane tablePane=new Pane();

        tablePane.getChildren().add(orderTable);
        return tablePane;
    }

    public Pane getOrders(Customer customer){
        String query="select orders.oid,products.name,products.price from orders inner join products on orders.product_id=products.pid where orders.customer_id="+customer.getId()+"";
        ObservableList<Product> orderList=getOrderList(query);
        return createTableFromList(orderList);

    }

    public static ObservableList<Product> getOrderList(String query){

        DatabaseConnection dbConn=new DatabaseConnection();
        ObservableList<Product> result= FXCollections.observableArrayList();
        try {
            ResultSet rs = dbConn.getQueryTable(query);
            if(rs!=null){
                while(rs.next()){
                    result.add(new Product(rs.getInt("oid"),rs.getString("name"),rs.getDouble("price")));
                }
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
