package client.Controller;

import client.Applic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    @FXML
    Label tip1;
    @FXML
    Label tip2;
    @FXML
    Label tip3;
    @FXML
    void registerClick(){
        applic.registerPage();
    };


    Applic applic;
    public void setApplic(Applic applic){
        this.applic = applic;
    }

    private void clearTextField(){
        usernameField.setText("");
        passwordField.setText("");
    }

    private void clearTips(){
        tip1.setText("");
        tip2.setText("");
    }
    @FXML
    private void LoginBtnClick() {
        //清除提示框
        clearTips();
        //检查输入框是否符合要求
        Check();
        //清除输入框
        clearTextField();
    }
    private void Check(){
        final String username = usernameField.getText();
        final String password = passwordField.getText();
        if(username.equals("")){
            tip1.setText("Username cannot be empty!");
            return;
        }
        if(password.equals("")){
            tip2.setText("Password cannot be empty!");
            return;
        }
        if(!applic.checkUsername(username)){
            tip1.setText("this account is not registered.");
            return;
        }
        if(!applic.checkPassword(username, password)){
            tip2.setText("wrong password");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}