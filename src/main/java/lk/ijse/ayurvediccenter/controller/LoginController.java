
package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.ayurvediccenter.App;
import lk.ijse.ayurvediccenter.dto.LoginDTO;
import lk.ijse.ayurvediccenter.model.LoginModel;

import java.sql.SQLOutput;


public class LoginController {

    public static int userId;
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;

    LoginModel loginModel = new LoginModel();

    
    @FXML
    private void login() {
        
        try{

            String username = usernameField.getText();
            String password = passwordField.getText();

            LoginDTO loginDTO = loginModel.verifyUser(username ,password);
        
            if(loginDTO != null){
                    LoginController.userId = loginDTO.getUserId();

                    SessionController.setUser(loginDTO.getUserId(), loginDTO.getRole());


                App.setRoot("UserView");

            }else{
                new Alert(Alert.AlertType.ERROR , "Invalid Username or Password!").show();
    
            }
        
        }catch(Exception e){
                
            e.printStackTrace();
        }
    }
    
    @FXML
    private void forgetPword(){
    }
    
    
    @FXML
    private void reset(){
        usernameField.setText("");
        passwordField.setText("");
    }

}
