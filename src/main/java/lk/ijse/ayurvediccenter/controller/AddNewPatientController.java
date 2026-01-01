package lk.ijse.ayurvediccenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.PatientDTO;
import lk.ijse.ayurvediccenter.model.PatientModel;

;import java.util.Optional;

public class AddNewPatientController {

    @FXML
    private TextArea fNameField;

    @FXML
    private TextArea lNameField;

    @FXML
    private TextArea addressField;

    @FXML
    private TextArea nicField;

    @FXML
    private TextArea contactnumberField;

    @FXML
    private TextArea genderField;

    @FXML
    private TextArea dateofbirthField;

    @FXML
    private Button saveButton;


    private final String PATIENT_ID_REGEX = "^[0-9]+$";
    private final String PATIENT_FIRST_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_LAST_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_ADDRESS_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_NIC_REGEX = "^([0-9]{9}[Vv]|[0-9]{12})$";
    private final String PATIENT_CONTACT_REGEX = "^[0-9]{10}$";
    private final String PATIENT_GENDER_REGEX = "(?i)^(male|female)$";
    private final String PATIENT_DATE_OF_BIRTH_REGEX = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

    PatientModel patientModel = new PatientModel();

    private PatientController patientController;

    public void setPatientController(PatientController patientController) {
        this.patientController = patientController;
    }


    @FXML
    private void handleSavePatient(){
        String fName=fNameField.getText().trim();
        String lName=lNameField.getText().trim();
        String address=addressField.getText().trim();
        String nic=nicField.getText().trim();
        String contactnumber=contactnumberField.getText().trim();
        String gender=genderField.getText().trim();
        String dateofbirth=dateofbirthField.getText().trim();


        if(!fName.matches(PATIENT_FIRST_NAME_REGEX)){
            new Alert(Alert.AlertType.ERROR,"Invalid first name format!", ButtonType.OK).show();
        }else if(!fName.matches(PATIENT_LAST_NAME_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid last name format!", ButtonType.OK).show();
        }else if(!address.matches(PATIENT_ADDRESS_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid address format!", ButtonType.OK).show();
        }else if(!nic.matches(PATIENT_NIC_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid nic format!", ButtonType.OK).show();
        }else if(!contactnumber.matches(PATIENT_CONTACT_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact number format!", ButtonType.OK).show();
        }else if(!dateofbirth.matches(PATIENT_DATE_OF_BIRTH_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid date format!", ButtonType.OK).show();
        }else if(!gender.matches(PATIENT_GENDER_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid gender format!", ButtonType.OK).show();
        }else{
            try{

                if(patientModel.isPatientExists(nic)) {
                    new Alert(Alert.AlertType.WARNING, "Patient already exists!").show();
                } else {

                    System.out.println(fName + "," + address + "," + nic + "," + contactnumber + "," + gender + "," + dateofbirth);
                    PatientDTO patientDTO = new PatientDTO(fName, lName, address, nic, contactnumber, gender, dateofbirth);
                    boolean result = patientModel.savePatient(patientDTO);

                    if (result) {
                        patientController.loadPatientTable();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText("Patient successfully added. Would you like to add another patient?");

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
                            patientController.loadPatientTable();
                        }


                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to save patient!", ButtonType.OK).show();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void cleanField(){
        fNameField.setText("");
        lNameField.setText("");
        addressField.setText("");
        nicField.setText("");
        contactnumberField.setText("");
        genderField.setText("");
        dateofbirthField.setText("");

    }
}
