package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.DoctorDTO;
import lk.ijse.ayurvediccenter.dto.EmployeeDTO;
import lk.ijse.ayurvediccenter.model.DoctorModel;
import lk.ijse.ayurvediccenter.model.EmployeeModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML private TextField addressField;

    @FXML private TextField contactField;

    @FXML private TextField emailField;

    @FXML private Text userIdField;

    @FXML private TextField fNameField;

    @FXML private TextField lNameField;

    @FXML private Text nameField;

    @FXML private TextField roleField;

    @FXML private Button saveButton;

    int emp_id;
    boolean isEmp;

    private EmpManagementController empManagementController;

    EmployeeModel employeeModel = new EmployeeModel();
    EmployeeDTO employeeDTO = new EmployeeDTO();

    DoctorModel doctorModel = new DoctorModel();
    DoctorDTO  doctorDTO = new DoctorDTO();

    private final String EMPLOYEE_FIRST_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private final String EMPLOYEE_LAST_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private final String EMPLOYEE_ADDRESS_REGEX = "^[a-zA-Z]{3,}$";
    private final String EMPLOYEE_CONTACT_REGEX = "^[0-9]{10}$";
    private final String EMPLOYEE_EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private final String EMPLOYEE_ROLE_REGEX = "^[a-zA-Z]{3,}$";

    public void setEmpManagementController(EmpManagementController empManagementController) {
        this.empManagementController = empManagementController;

        cleanField();
        enableEditMode();

        try {
            emp_id = employeeModel.getNextEmpId();
            userIdField.setText(String.valueOf(emp_id));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        saveButton.setVisible(false);
        saveButton.setManaged(false);

        getUserInfo();
        loadProfileInfo();


    }

    @FXML
    public void getUserInfo() {
        try {
            System.out.println(LoginController.userId);
            isEmp = employeeModel.getEmpState();
            System.out.println(isEmp);
            if (isEmp) {
                employeeDTO = employeeModel.getEmployeeId(LoginController.userId);

            }else{
                doctorDTO = doctorModel.getDoctorId(LoginController.userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void loadProfileInfo(){
        try {
            if (isEmp) {
                employeeDTO = employeeModel.searchEmployee(employeeDTO.getEmp_id());
                    userIdField.setText(String.valueOf(employeeDTO.getEmp_id()));
                    nameField.setText(employeeModel.username);
                    fNameField.setText(employeeDTO.getFName());
                    lNameField.setText(employeeDTO.getLName());
                    addressField.setText(employeeDTO.getAddress());
                    contactField.setText(employeeDTO.getContact_num());
                    emailField.setText(employeeDTO.getEmail());
                    roleField.setText(employeeDTO.getRole());


            }else{
                doctorDTO = doctorModel.searchDoctor(doctorDTO.getDoctor_id());
                    userIdField.setText(String.valueOf(LoginController.userId));
                    nameField.setText(doctorModel.username);
                    fNameField.setText(doctorDTO.getFname());
                    lNameField.setText(doctorDTO.getLname());
                    addressField.setText(doctorDTO.getAddress());
                    contactField.setText(doctorDTO.getContact_num());
                    emailField.setText(doctorDTO.getEmail());
                    roleField.setText("Doctor");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    public void handleSaveEmployee(){

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
            try{
                System.out.println(emp_id+","+fName +","+ lName+","+address+","+contactNumber+","+email+","+role);
                EmployeeDTO employeeDTO = new EmployeeDTO(emp_id,fName,lName,address,contactNumber,email,role);
                boolean result = employeeModel.saveEmployee(employeeDTO);

                if(result){
                    empManagementController.loadEmployeeTable();
                    Alert alert =new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("Employee successfully added. Would you like to add another Employee?");

                    ButtonType buttonYes = new ButtonType("Yes");
                    ButtonType buttonNo = new ButtonType("No");
                    alert.getButtonTypes().setAll(buttonYes, buttonNo);

                    Optional<ButtonType> results = alert.showAndWait();


                    if (results.isPresent() && results.get() == buttonYes) {
                        cleanField();


                    } else {

                        Stage currentStage = (Stage) saveButton.getScene().getWindow();
                        currentStage.close();
                        cleanField();
                        empManagementController.loadEmployeeTable();
                    }



                }else {
                    new Alert(Alert.AlertType.ERROR,"Failed to save patient!", ButtonType.OK).show();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    private void cleanField(){
        fNameField.clear();
        lNameField.clear();
        addressField.clear();
        contactField.clear();
        emailField.clear();
        roleField.clear();
    }


    private void enableEditMode() {

        // hide name text
        nameField.setVisible(false);
        nameField.setManaged(false);

        // show save button
        saveButton.setVisible(true);
        saveButton.setManaged(true);

        // enable editing
        fNameField.setEditable(true);
        lNameField.setEditable(true);
        addressField.setEditable(true);
        contactField.setEditable(true);
        emailField.setEditable(true);
        roleField.setEditable(true);
    }


}
