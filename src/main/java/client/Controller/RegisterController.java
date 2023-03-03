package client.Controller;

import client.Applic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label tip1;
    @FXML
    private Label tip2;
    @FXML
    private Label tip3;

    Applic applic = new Applic();
    public void setApplic(Applic applic){
        this.applic = applic;
    }
    @FXML
    private void RegisterBtn(){
    }
    private void clearTextField(){
        usernameField.setText("");
        passwordField.setText("");
    }

    private void clearTips(){
        tip1.setText("");
        tip2.setText("");
    }
    private boolean Check(){
        final String username = usernameField.getText();
        final String password = passwordField.getText();
        if(username.equals("")){
            tip1.setText("Username cannot be empty!");
            return false;
        }
        if(password.equals("")){
            tip2.setText("Password cannot be empty!");
            return false;
        }
        if(applic.checkUsername(username)){
            tip1.setText("this account is registered.");

            return false;
        }
        applic.addUser(username, password);
        return true;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
