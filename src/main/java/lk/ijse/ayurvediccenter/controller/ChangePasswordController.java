package lk.ijse.ayurvediccenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.ayurvediccenter.dto.EmployeeDTO;
import lk.ijse.ayurvediccenter.dto.LoginDTO;
import lk.ijse.ayurvediccenter.model.LoginModel;

import static lk.ijse.ayurvediccenter.controller.LoginController.userId;

public class ChangePasswordController {

    @FXML private PasswordField confirmPwordField;

    @FXML private PasswordField currPwordField;

    @FXML private PasswordField newPwordField;

    @FXML private TextField usernameField;

    private final String EMPLOYEE_NEW_PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$\n";

    LoginModel loginModel = new LoginModel();
    private EmployeeDTO employeeDTO;

    public void initData(EmployeeDTO employeeDTO){
        this.employeeDTO = employeeDTO;

    }

    @FXML
    public void updatePassword(ActionEvent event) {
        try {
            String username = usernameField.getText();
            String password = currPwordField.getText();

            LoginDTO loginDTO = loginModel.verifyPassword(userId, username, password);

            if (loginDTO != null) {
                String newPassword = newPwordField.getText();
                String confirmNewPassword = confirmPwordField.getText();

                if (!newPassword.matches(EMPLOYEE_NEW_PASSWORD_REGEX)) {
                    new Alert(Alert.AlertType.ERROR, "Invalid New Password format!", ButtonType.OK).show();
                } else if (!confirmNewPassword.matches(EMPLOYEE_NEW_PASSWORD_REGEX)) {
                    new Alert(Alert.AlertType.ERROR, "Invalid Confirm New Password format!", ButtonType.OK).show();
                } else {

                    if (newPassword.equals(confirmNewPassword)) {
                        boolean result = loginModel.updatePassword(employeeDTO.getEmp_id(), userId, username, confirmNewPassword);

                        if (result) {
                            new Alert(Alert.AlertType.INFORMATION, "Password Updated successfully!", ButtonType.OK).show();
                            resetFields();

                        } else {
                            new Alert(Alert.AlertType.ERROR, "Failed to update Password!", ButtonType.OK).show();
                        }

                    } else {
                        new Alert(Alert.AlertType.ERROR, "New Password and Confirmed Password mismatch!", ButtonType.OK).show();
                    }
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Username or Password!", ButtonType.OK).show();
            }


        }catch(Exception e){
            e.printStackTrace();
        }


    }


    private void resetFields() {
        usernameField.clear();
        confirmPwordField.clear();
        newPwordField.clear();
        currPwordField.clear();
    }
}
