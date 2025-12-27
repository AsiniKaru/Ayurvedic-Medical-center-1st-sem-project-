package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.DoctorDTO;
import lk.ijse.ayurvediccenter.dto.EmployeeDTO;
import lk.ijse.ayurvediccenter.model.DoctorModel;
import lk.ijse.ayurvediccenter.model.EmployeeModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingController implements Initializable {

    @FXML private TextField addressField;

    @FXML private TextField contactField;

    @FXML private TextField emailField;

    @FXML private Text userIdField;

    @FXML private TextField fNameField;

    @FXML private TextField lNameField;

    @FXML private Text nameField;

    @FXML private Text usernameField;

    @FXML private TextField roleField;

    @FXML private AnchorPane mainContain;

    @FXML private BorderPane profileView;

    boolean isEmp;
    static String Username;

    EmployeeModel employeeModel = new EmployeeModel();
    EmployeeDTO employeeDTO = new EmployeeDTO();

    DoctorModel doctorModel = new DoctorModel();
    DoctorDTO doctorDTO = new DoctorDTO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getUserInfo();
        loadProfileInfo();

        if(isEmp == false){
            usernameField.setText(doctorModel.username);

        }else{
            usernameField.setText(employeeModel.username);

        }
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
    public void onActionProfile(){ navigateTo("/lk/ijse/ayurvediccenter/view/Profile.fxml"); }

    @FXML
    public void onActionEditProfile(){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/lk/ijse/ayurvediccenter/view/EditProfile.fxml"));

            AnchorPane pane = loader.load(); // IMPORTANT

            EditProfileController controller = loader.getController();
            controller.initData(employeeDTO); // patientDTO already exists


            mainContain.getChildren().clear();
            mainContain.getChildren().add(pane);


            pane.prefWidthProperty().bind(mainContain.widthProperty());
            pane.prefHeightProperty().bind(mainContain.heightProperty());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onActionChangePassword(){

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/lk/ijse/ayurvediccenter/view/ChangePassword.fxml"));

            AnchorPane pane = loader.load(); // IMPORTANT

            ChangePasswordController controller = loader.getController();
            controller.initData(employeeDTO); // patientDTO already exists


            mainContain.getChildren().clear();
            mainContain.getChildren().add(pane);


            pane.prefWidthProperty().bind(mainContain.widthProperty());
            pane.prefHeightProperty().bind(mainContain.heightProperty());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onActionEmployeeManage(){ navigateTo("/lk/ijse/ayurvediccenter/view/EmpManagement.fxml"); }

    @FXML
    public void onActionPrivacyPolicy(){ navigateTo("/lk/ijse/ayurvediccenter/view/PrivacyPolicy.fxml"); }

    @FXML
    public void onActionDashboard(){

        try {
            Stage stage = (Stage) profileView.getScene().getWindow();

            Parent root = FXMLLoader.load(
                    getClass().getResource("/lk/ijse/ayurvediccenter/view/UserView.fxml")
            );

            stage.setScene(new Scene(root));

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Cannot load Dashboard").show();
            e.printStackTrace();
        }
    }

    @FXML
    public void navigateTo(String path) {
        try {
            mainContain.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            anchorPane.prefWidthProperty().bind(mainContain.widthProperty());
            anchorPane.prefHeightProperty().bind(mainContain.heightProperty());
            mainContain.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }


}
