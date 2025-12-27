package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lk.ijse.ayurvediccenter.dto.EmployeeDTO;
import lk.ijse.ayurvediccenter.controller.SettingController;
import lk.ijse.ayurvediccenter.model.EmployeeModel;

import java.sql.SQLException;

public class EditProfileController {

    @FXML private TextField addressField;

    @FXML private TextField contactField;

    @FXML private TextField emailField;

    @FXML private Text userIdField;

    @FXML private TextField fNameField;

    @FXML private TextField lNameField;

    @FXML private Text nameField;

    @FXML private TextField roleField;

    private final String EMPLOYEE_FIRST_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private final String EMPLOYEE_LAST_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private final String EMPLOYEE_ADDRESS_REGEX = "^[a-zA-Z]{3,}$";
    private final String EMPLOYEE_CONTACT_REGEX = "^[0-9]{10}$";
    private final String EMPLOYEE_EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private final String EMPLOYEE_ROLE_REGEX = "^[a-zA-Z]{3,}$";


    EmployeeModel employeeModel =new EmployeeModel();

    private EmployeeDTO employeeDTO;

    private EmpManagementController empManagementController;

    public void setInitData(EmployeeDTO employeeDTO ,EmpManagementController empManagementController) throws SQLException {
        this.employeeDTO = employeeDTO;
        this.empManagementController = empManagementController;

        initData(employeeDTO);
    }

    public void initData(EmployeeDTO employeeDTO ) throws SQLException{
        this.employeeDTO = employeeDTO;

        System.out.println(employeeDTO);
        userIdField.setText(String.valueOf(employeeDTO.getEmp_id()));
        nameField.setText(employeeDTO.getFName());
        fNameField.setText(employeeDTO.getFName());
        lNameField.setText(employeeDTO.getLName());
        addressField.setText(employeeDTO.getAddress());
        contactField.setText(employeeDTO.getContact_num());
        emailField.setText(employeeDTO.getEmail());
        roleField.setText(employeeDTO.getRole());
    }


    @FXML
    public void handleUpdateEmployee() {
        try {
            String fName =fNameField.getText();
            String lName = lNameField.getText().trim();
            String address = addressField.getText().trim();
            String contactNumber = contactField.getText().trim();
            String email = emailField.getText().trim();
            String role = roleField.getText().trim();

            if (!fName.matches(EMPLOYEE_FIRST_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid name format!", ButtonType.OK).show();
            } else if (!lName.matches(EMPLOYEE_LAST_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid name format!", ButtonType.OK).show();
            } else if (!address.matches(EMPLOYEE_ADDRESS_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid address format!", ButtonType.OK).show();
            } else if (!contactNumber.matches(EMPLOYEE_CONTACT_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid contactNumber format!", ButtonType.OK).show();
            } else if (!email.matches(EMPLOYEE_EMAIL_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid email format!", ButtonType.OK).show();
            } else if (!role.matches(EMPLOYEE_ROLE_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid role format!", ButtonType.OK).show();
            }else {
                System.out.println(fName + "," + lName + "," + address + "," + contactNumber + "," + email + "," + role );
                EmployeeDTO empDTO = new EmployeeDTO(employeeDTO.getEmp_id(), fName, lName, address, contactNumber, email, role);

                boolean result = employeeModel.updateEmployee(empDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Employee has been Updated successfully!", ButtonType.OK).show();
                    empManagementController.loadEmployeeTable();


                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update Employee!", ButtonType.OK).show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
