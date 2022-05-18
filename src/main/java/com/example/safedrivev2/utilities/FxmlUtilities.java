package com.example.safedrivev2.utilities;


import com.example.safedrivev2.SafeDrive;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlUtilities {

    public static void sceneTransiton(Button button, String fxmlFileName, int sceneWidth, int sceneHeight)throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(SafeDrive.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load(), sceneWidth, sceneHeight);
        stage.setTitle("SafeDrive!");
        stage.setScene(scene);
        stage.show();
    }

    public static void sceneTransiton1(AnchorPane anchor, String fxmlFileName, int sceneWidth, int sceneHeight)throws IOException {
        Stage stage = (Stage) anchor.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(SafeDrive.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load(), sceneWidth, sceneHeight);
        stage.setTitle("SafeDrive!");
        stage.setScene(scene);
        stage.show();
    }

}
