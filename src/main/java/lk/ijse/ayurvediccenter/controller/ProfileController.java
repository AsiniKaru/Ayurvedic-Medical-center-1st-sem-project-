package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lk.ijse.ayurvediccenter.controller.LoginController;
import lk.ijse.ayurvediccenter.dto.DoctorDTO;
import lk.ijse.ayurvediccenter.dto.EmployeeDTO;
import lk.ijse.ayurvediccenter.model.DoctorModel;
import lk.ijse.ayurvediccenter.model.EmployeeModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private TextField addressField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField emailField;

    @FXML
    private Text userIdField;

    @FXML
    private TextField fNameField;

    @FXML
    private TextField lNameField;

    @FXML
    private Text nameField;



    @FXML
    private TextField roleField;

    boolean isEmp;

    EmployeeModel employeeModel = new EmployeeModel();
    EmployeeDTO employeeDTO = new EmployeeDTO();

    DoctorModel doctorModel = new DoctorModel();
    DoctorDTO  doctorDTO = new DoctorDTO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                    fNameField.setText(employeeDTO.getfName());
                    lNameField.setText(employeeDTO.getlName());
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





}
