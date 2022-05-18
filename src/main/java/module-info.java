module com.example.safedrivev2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.safedrivev2 to javafx.fxml;
    exports com.example.safedrivev2;
    exports com.example.safedrivev2.controllers;
    opens com.example.safedrivev2.controllers to javafx.fxml;
}