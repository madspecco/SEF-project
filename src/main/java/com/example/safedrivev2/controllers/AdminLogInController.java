package com.example.safedrivev2.controllers;

import com.example.safedrivev2.utilities.FxmlUtilities;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public void setAdminCancelButtonOnAction(ActionEvent event) throws IOException {
        FxmlUtilities.sceneTransiton(cancelAdminButton,"interfaces/chooseAccountType.fxml",1280,720);
    }
    public void setAdminRegisterButtonOnAction(ActionEvent event) throws IOException {
        FxmlUtilities.sceneTransiton(registerAdminButton,"interfaces/adminRegister.fxml",1280,720);
    }

    public void setAdminLoginButtonOnAction(ActionEvent event) throws IOException {
        FxmlUtilities.sceneTransiton(registerAdminButton,"interfaces/adminInterface.fxml",1280,720);
    }

}
