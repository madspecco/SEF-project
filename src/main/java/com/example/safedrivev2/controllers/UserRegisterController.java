package com.example.safedrivev2.controllers;

import com.example.safedrivev2.services.UserServices;
import com.example.safedrivev2.utilities.FxmlUtilities;
import com.example.safedrivev2.utilities.PassBasedEnc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class UserRegisterController {
    @FXML
    private Button registerButton;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button cancelButton;

    public void setCancelButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        FxmlUtilities.sceneTransiton(cancelButton,"interfaces/userLogIn.fxml",1280,720);
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException {
        if(setPasswordField.getText().equals(confirmPasswordField.getText())){

            String firstname = firstnameTextField.getText();
            String lastname = lastnameTextField.getText();
            String username = usernameTextField.getText();
            String email = emailTextField.getText();                    // no need for email , will delete later
            String saltvalue = PassBasedEnc.getSaltvalue(30);
            String password = setPasswordField.getText();
            String encryptedPass = PassBasedEnc.generateSecurePassword(password, saltvalue);



            /*String firstname = firstnameTextField.getText();
            String lastname = lastnameTextField.getText();
            String email = emailTextField.getText();
            String username = usernameTextField.getText();
            String saltvalue = PassBasedEnc.getSaltvalue(30);
            String encryptedPass = PassBasedEnc.generateSecurePassword(setPasswordField.getText(), saltvalue);*/

            UserServices.registerUser(firstname, lastname, email, username, saltvalue, encryptedPass);        // remove email parameter from here and actual function
            FxmlUtilities.sceneTransiton(registerButton,"interfaces/userLogIn.fxml",1280,720);

        }else{
            confirmPasswordLabel.setText("Password does not match");
        }
    }




}
