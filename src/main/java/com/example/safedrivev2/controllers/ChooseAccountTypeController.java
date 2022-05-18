package com.example.safedrivev2.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import com.example.safedrivev2.utilities.FxmlUtilities;


import java.io.File;
import java.util.ResourceBundle;

import java.net.URL;

public class ChooseAccountTypeController implements Initializable {
    @FXML
    private ImageView logoImageView;
    @FXML
    private AnchorPane userAnchor;
    @FXML
    private AnchorPane adminAnchor;
    @FXML
    private Label chooseAccType;
    @FXML
    private Label accType;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("src/main/resources/com/example/safedrivev2/images/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);
    }

    public void userAnchorOnAction() {
        try {

            FxmlUtilities.sceneTransiton1(userAnchor,"interfaces/userLogIn.fxml",1280,720);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            Platform.exit();
        }
    }
    public void adminAnchorOnAction() {
        try {
            FxmlUtilities.sceneTransiton1(adminAnchor,"interfaces/adminLogIn.fxml",1280,720);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            Platform.exit();
        }
    }




}