package com.example.bolosui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainController {
    @FXML
    private Label Status;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button Login;

    public void userLogIn(ActionEvent event) throws IOException{
        checkLogin();
    }

    @FXML
    protected void checkLogin(ActionEvent event) {
        Main m = new Main();
        if(txtUsername.getText().equals("username") && txtPassword.getText().toString().equals("pass"))
        {
            Status.setText("Login is successful!");
           // m.changeScene("afterLogin.fxml");      // we need to create afterLogin.fxml :D
        }
        else if(txtUsername.getText().isEmpty() && txtPassword.getText().toString().isEmpty()){
            Status.setText("Please enter your data!");
        }
        else{
            Status.setText("Login failed! \n Please try again!");
        }
    }
}