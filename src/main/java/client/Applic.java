package client;

import client.Controller.LoginController;
import client.Controller.RegisterController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.DAO.UserDAO;
/**
 * 用于切换窗口
 */
public class Applic extends Application {
    private static final String LoginView = "Login.fxml";
    private static final String RegisterView= "/view/Register.fxml";
    public Stage primaryStage;
    public UserDAO userDAO;
    public LoginController loginController;
    public RegisterController registerController;
    public Applic(){}
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage=primaryStage;
        primaryStage.setTitle("Chat Room");
        loginPage();
        primaryStage.show();
    }
    public boolean checkUsername(String username){
        return username.equals(userDAO.searchUsername(username));
    }
    public boolean checkPassword(String username, String password){
        return password.equals(userDAO.searchPassword(username));
    }

    public void loginPage(){
        try {

            primaryStage.setResizable(false);
            loginController = (LoginController) showContent(LoginView);
            loginController.setApplic(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void registerPage(){
        try {
            //primaryStage.setWidth(560);
            //primaryStage.setHeight(350);
            primaryStage.setResizable(false);
            registerController = (RegisterController) showContent(RegisterView);
            registerController.setApplic(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addUser(String username, String password){
        userDAO.addUser(username, password);
    }
    //this method is used to get a fxml page.
    public Initializable showContent (String location) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Applic.class.getResource(location));
        Parent page = loader.load();
        Platform.runLater(() -> {
            primaryStage.setScene(new Scene(page));
            primaryStage.sizeToScene();
        });

        return (Initializable) loader.getController();
    }


}
