package com.example.safedrivev2.controllers;

import com.example.safedrivev2.services.UserServices;
import com.example.safedrivev2.utilities.FxmlUtilities;
import com.example.safedrivev2.utilities.PassBasedEnc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminRegisterController {
    @FXML
    private Button registerAdminButton;
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
    private Button cancelAdminButton;

    public void setCancelAdminButtonOnAction(ActionEvent event) throws IOException {
        FxmlUtilities.sceneTransiton(cancelAdminButton,"interfaces/adminLogIn.fxml",1280,720);
    }


    public void registerButtonOnAction(ActionEvent event) throws IOException {
        if(setPasswordField.getText().equals(confirmPasswordField.getText())){

            String firstname = firstnameTextField.getText();
            String lastname = lastnameTextField.getText();
            String username = usernameTextField.getText();
            //String email = emailTextField.getText();                    // no need for email , will delete later
            String saltvalue = PassBasedEnc.getSaltvalue(30);
            String password = setPasswordField.getText();
            String encryptedPass = PassBasedEnc.generateSecurePassword(password, saltvalue);

            UserServices.registerAdmin(firstname, lastname, username, saltvalue, encryptedPass);        // remove email parameter from here and actual function
            FxmlUtilities.sceneTransiton(registerAdminButton,"interfaces/adminLogIn.fxml",1280,720);

        }else{
            confirmPasswordLabel.setText("Password does not match");
        }
    }
}