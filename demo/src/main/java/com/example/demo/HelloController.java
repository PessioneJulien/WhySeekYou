package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloController {
    @FXML
    private Button switchButton;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmField;
    @FXML
    public Button signupButton;

    @FXML
    public void initialize() {
        switchButton.setOnAction(event -> {
            try {
                Parent root;
                if (switchButton.getText().equals("Switch to Signup")) {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUp.fxml")));
                    switchButton.setText("Switch to Login");
                } else {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
                    switchButton.setText("Switch to Signup");
                }
                Stage stage = (Stage) switchButton.getScene().getWindow();
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public void setEmailField(TextField emailField) {
        this.emailField = emailField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public void setConfirmField(PasswordField confirmField) {
        this.confirmField = confirmField;
    }

    public void setSignupButton(Button signupButton) {
        this.signupButton = signupButton;
    }

    public String getResponseMessage() {
        return null;
    }
}