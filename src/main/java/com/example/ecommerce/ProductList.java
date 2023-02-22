package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ProductList {

    public TableView<Product> prodTable;

    public Pane getAllProducts(){



        ObservableList<Product> productList=Product.getAllProducts();

        return createTableFromList(productList);
    }

    public Pane createTableFromList(ObservableList<Product> productList){
        TableColumn id=new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name=new TableColumn("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price =new TableColumn("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));



        prodTable=new TableView<>();
        prodTable.setItems(productList);
        prodTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        prodTable.getColumns().addAll(id,name,price);

        Pane tablePane=new Pane();

        tablePane.getChildren().add(prodTable);
        return tablePane;
    }

    public Pane productsInCart(ObservableList<Product> productList){
        return createTableFromList(productList);
    }

    public Product getSelectedProduct(){
        return prodTable.getSelectionModel().getSelectedItem();
    }

}
