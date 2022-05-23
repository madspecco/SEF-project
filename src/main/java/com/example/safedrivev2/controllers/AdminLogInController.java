package com.example.safedrivev2.controllers;

import com.example.safedrivev2.exceptions.InexistentUserException;
import com.example.safedrivev2.exceptions.UserPasswordInvalidException;
import com.example.safedrivev2.model.User;
import com.example.safedrivev2.services.UserServices;
import com.example.safedrivev2.utilities.FxmlUtilities;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


import java.io.IOException;

public class AdminLogInController {
    @FXML
    private Button registerAdminButton;
    @FXML
    private Button loginAdminButton;
    @FXML
    private Button cancelAdminButton;

    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Label loginMessageLabel;

    public void setAdminCancelButtonOnAction(ActionEvent event) throws IOException {
        FxmlUtilities.sceneTransiton(cancelAdminButton,"interfaces/chooseAccountType.fxml",1280,720);
    }
    public void setAdminRegisterButtonOnAction(ActionEvent event) throws IOException {
        FxmlUtilities.sceneTransiton(registerAdminButton,"interfaces/adminRegisterV2.fxml",1280,720);
    }

   /* public void setAdminLoginButtonOnAction(ActionEvent event) throws IOException {
        FxmlUtilities.sceneTransiton(registerAdminButton,"interfaces/adminInterface.fxml",1280,720);
    }*/




    private void setUserInstance(String username) {
        User currentUser = new User();
        UserServices.initializeUser(currentUser, username);
    }



    public void setAdminLoginButtonOnAction(ActionEvent event){
        if(userTextField.getText().isBlank() ==false && enterPasswordField.getText().isBlank()==false){

            try{
                if(UserServices.validateLoginAdmin(userTextField.getText(), enterPasswordField.getText(),loginAdminButton)==true){

                    setUserInstance(userTextField.getText());
                    //setStoreInvetoryInstance();
                    //setStoreCouponList();
                    FxmlUtilities.sceneTransiton(loginAdminButton,"interfaces/adminInterface.fxml",1280,720);
                }
            }catch(InexistentUserException exception1){
                // loginMessageLabel.setText(exception1.getMessage());
                loginMessageLabel.setText("User does not exist! Please register!");
            }catch(UserPasswordInvalidException exception2){
                //loginMessageLabel.setText(exception2.getMessage());
                loginMessageLabel.setText("Invalid password! Try again!");
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
                Platform.exit();
            }

        }else {
            loginMessageLabel.setText("Please enter username and password.");
        }
    }


}





