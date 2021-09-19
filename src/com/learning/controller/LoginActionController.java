package com.learning.controller;

import com.learning.common.EmailResponse;
import com.learning.model.EmailAccount;
import com.learning.service.LoginService;
import com.learning.view.EmailManager;
import com.learning.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginActionController extends BaseController implements Initializable {

    @FXML
    private Button loginButton;

    @FXML
    private TextField emailAddressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    public LoginActionController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    void loginButtonAction() {
        if(fieldsAreValid()) {
            EmailAccount emailAccount = new EmailAccount(emailAddressField.getText(),passwordField.getText());
            LoginService loginService = new LoginService(emailManager,emailAccount);
            loginService.start();
            loginService.setOnSucceeded(event->{
                EmailResponse emailResponse = loginService.getValue(); //Like a Future
                switch(emailResponse){
                    case SUCCESSFUL:
                        System.out.println("Yay inside!");
                        Stage stage = (Stage) errorLabel.getScene().getWindow();
                        viewFactory.showMainWindow();
                        viewFactory.closeStage(stage);
                        break;
                    default:
                        break;
                }

            });

        }
    }

    private boolean fieldsAreValid() {
        if(emailAddressField.getText().isBlank()){
            errorLabel.setText("Please fill in the Email!");
            return false;
        }
        if(passwordField.getText().isBlank()){
            errorLabel.setText("Please fill in the Password!");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailAddressField.setText("tanyahorasweet@gmail.com");
        passwordField.setText("tanyahorasweet");
    }
}