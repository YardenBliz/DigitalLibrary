module org.example.digitallibraryclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;


    exports org.example.digitallibraryclient;
    exports org.example.digitallibraryclient.model;
    exports org.example.digitallibraryclient.controller;
    opens org.example.digitallibraryclient to javafx.fxml;
    opens org.example.digitallibraryclient.controller to com.google.gson, javafx.fxml;
    opens org.example.digitallibraryclient.model to com.google.gson, javafx.fxml;

}