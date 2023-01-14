package org.FullProject;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainInterface extends Application {
    public static void main(String[] args) {
        SpringApplication.run(MainInterface.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }
}
