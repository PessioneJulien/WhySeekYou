package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StartInterface extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader2 = new FXMLLoader(StartLogin.class.getResource("Interface.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(),900, 600);
        stage.setTitle("Chat");
        stage.setScene(scene2);
        stage.show();
    }
}
