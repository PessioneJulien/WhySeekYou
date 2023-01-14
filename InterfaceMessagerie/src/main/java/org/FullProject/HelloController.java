package org.FullProject;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloController {
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    //...
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
        headers.setContentType(MediaType.APPLICATION_JSON);
        signupButton.setOnAction(event -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirm = confirmField.getText();
            if (password.equals(confirm)) {
                try {
                    String requestJson = "{\"username\":\"" + username + "\",\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
                    HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
                    HttpEntity<String> response = restTemplate.postForEntity("http://localhost:8080/api/signup", entity, String.class);
                    System.out.println(response.getBody());
                } catch (Exception e) {
                    e.printStackTrace();
                }
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