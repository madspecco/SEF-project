package com.example.safedrivev2.controllers;


import com.example.safedrivev2.exceptions.InexistentUserException;
import com.example.safedrivev2.exceptions.UserPasswordInvalidException;
import com.example.safedrivev2.model.User;
import com.example.safedrivev2.services.UserServices;
import com.example.safedrivev2.utilities.FxmlUtilities;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;



import java.io.IOException;
import java.util.ArrayList;


public class UserLogInController {

    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;



    @FXML
    private void setUserInstance(String username) {
        User currentUser = new User();
        UserServices.initializeUser(currentUser,username);
       // InventoryServices.initializeUserInventory(currentUser, currentUser.getInventoryId());
        //UserHolder holder = UserHolder.getInstance();
        //holder.setUser(currentUser);
    }

    /*
    private void setStoreInvetoryInstance() {
        ArrayList<Item> storeInventory = new ArrayList<>();
        InventoryServices.initializeStoreInventory(storeInventory);
        StoreInventoryHolder holder = StoreInventoryHolder.getInstance();
        holder.setStoreInventory(storeInventory);
    }

    private void setStoreCouponList(){
        ArrayList<Coupon> storeCouponList = new ArrayList<>();
        WalletServices.loadStoreCoupons(storeCouponList);
        StoreCouponHolder holder = StoreCouponHolder.getInstance();
        holder.setStoreCouponList(storeCouponList);
    }*/

    public void setCancelButtonOnAction(ActionEvent event) throws IOException {

        FxmlUtilities.sceneTransiton(cancelButton,"interfaces/chooseAccountType.fxml",1280,720);
    }
    public void setRegisterButtonOnAction(ActionEvent event) throws IOException {
        FxmlUtilities.sceneTransiton(registerButton,"interfaces/userRegister.fxml",1280,720);
    }


    public void loginButtonOnAction(ActionEvent event){
        if(userTextField.getText().isBlank() ==false && enterPasswordField.getText().isBlank()==false){

            try{
                if(UserServices.validateLogin(userTextField.getText(), enterPasswordField.getText(),loginButton)==true){

                    setUserInstance(userTextField.getText());
                    //setStoreInvetoryInstance();
                    //setStoreCouponList();
                    FxmlUtilities.sceneTransiton(loginButton,"interfaces/userInterface.fxml",1280,720);
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