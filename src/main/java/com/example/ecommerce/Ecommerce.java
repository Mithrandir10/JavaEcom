package com.example.ecommerce;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Ecommerce extends Application {

    private final int width=600,height=400,headerLine=50;
    ProductList pd=new ProductList();
    Pane bodyPane;
    Customer loggedInCustomer;
    Button loginButton ;
    Button SignupButton;
    Label welcomeLabel=new Label("Welcome ");

    private void showDialog(String s1,String s2){
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle(s1);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(s2);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        //Setting the label
        

            dialog.showAndWait();

    }
    private GridPane footerBar(){
        GridPane footer=new GridPane();
        Button buyButton=new Button("Buy Now");
        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product prod=pd.getSelectedProduct();
                boolean OrderStatus=false;
                if(pd!=null && loggedInCustomer!=null){
                    Order or=new Order();
                    OrderStatus=or.placeOrder(loggedInCustomer,prod);
                }

                if(OrderStatus==true){
                      showDialog("Order Status","Order Placed Successfully");
                }
            }
        });
        footer.setTranslateX(20);
        footer.setTranslateY(10);
        footer.setVgap(10);
        footer.setHgap(10);
        footer.add(buyButton,0,1);
        footer.setTranslateY(height + headerLine);
        return footer;
    }
    private GridPane headerBar(){
        GridPane header =new GridPane();
        TextField txt1=new TextField();
        Button search1=new Button("Search");
        search1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.setTranslateX(50);
                bodyPane.setTranslateX(20);
                bodyPane.getChildren().add(pd.getAllProducts());
            }
        });
         header.setHgap(10);
        loginButton=new Button("Sign In");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });
        SignupButton=new Button("Sign Up");
        SignupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(SignupPage());
            }
        });
        header.add(loginButton,3,0);
        header.add(welcomeLabel,6,0);
        header.add(SignupButton,4,0);
        header.setHgap(10);
        header.add(txt1,0,0);
        header.add(search1,1,0);

        return header;
    }

    private GridPane SignupPage(){
        GridPane singupPane=new GridPane();
        singupPane.setTranslateY(60);
        singupPane.setHgap(10);
        singupPane.setVgap(10);
        Label userLabel=new Label("User ID");
        Label plabel=new Label("Password");
        Label emailLabel=new Label("Email");
        TextField userText=new TextField();
        userText.setPromptText("Input the Name");
        TextField emailText=new TextField();
        emailText.setPromptText("Input Email ID");
        PasswordField password=new PasswordField();
        password.setPromptText("Set Password");
        Button sbutton=new Button("Sign Up");
        sbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user=userText.getText();
                String pass=password.getText();
                String email=emailText.getText();
                boolean SignedInCustomer=SignUp.CustomerSignUp(user,pass,email);
                if(SignedInCustomer==true){
                    showDialog("Sign In","User Id created Successfully");
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());
                }
            }
        });
        singupPane.add(userLabel,0,0);
        singupPane.add(userText,1,0);
        singupPane.add(emailLabel,0,1);
        singupPane.add(emailText,1,1);
        singupPane.add(plabel,0,2);
        singupPane.add(password,1,2);
        singupPane.add(sbutton,0,3);


        return singupPane;
    }
    private GridPane loginPage(){
        GridPane loginPane =new GridPane();
        loginPane.setTranslateY(50);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        Label userLabel=new Label("User ID");
        Label pLabel=new Label("Password");
        TextField userText=new TextField();
        userText.setPromptText("Enter User Name");
        PasswordField password=new PasswordField();
        password.setPromptText("Enter Password");

        Button loginButton=new Button("Login");
        Label messageLabel=new Label("Login - Message");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userText.getText();
                String pass = password.getText();
                loggedInCustomer=Login.customerLogin(user,pass);
                if(loggedInCustomer!=null){
                    messageLabel.setText("Login Successful");
                    welcomeLabel.setText("Welcome "+ loggedInCustomer.getName());
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(pd.getAllProducts());

                }else{
                    messageLabel.setText("Invalid Username or Password");
                }
            }
        });


        loginPane.add(userLabel,0,0);
        loginPane.add(userText,1,0);
        loginPane.add(pLabel,0,1);
        loginPane.add(password,1,1);
        loginPane.add(loginButton,0,2);
        loginPane.add(messageLabel,1,2);

        return loginPane;
    }

    private Pane createContent(){
        Pane root=new Pane();
        root.setPrefSize(width,height + 2*headerLine);

        bodyPane=new Pane();
        bodyPane.setPrefSize(width,height);
        bodyPane.setTranslateY(headerLine);
        bodyPane.setTranslateX(10);
        bodyPane.getChildren().add(loginPage());
        root.getChildren().addAll(headerBar(),bodyPane,footerBar());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(Ecommerce.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Ecommerce");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}