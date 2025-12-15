package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import lk.ijse.ayurvediccenter.dto.PatientDTO;
import lk.ijse.ayurvediccenter.model.PatientModel;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewPatientController {

    @FXML
    private TextArea nameField;

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


    private final String PATIENT_ID_REGEX = "^[0-9]+$";
    private final String PATIENT_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_ADDRESS_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_NIC_REGEX = "^([0-9]{9}[Vv]|[0-9]{12})$";
    private final String PATIENT_CONTACT_REGEX = "^[0-9]{10}$";
    private final String PATIENT_GENDER_REGEX = "(?i)^(male|female)$";
    private final String PATIENT_DATE_OF_BIRTH_REGEX = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

    PatientModel patientModel = new PatientModel();

    @FXML
    private void handleSavePatient(){
        String name=nameField.getText().trim();
        String address=addressField.getText().trim();
        String nic=nicField.getText().trim();
        String contactnumber=contactnumberField.getText().trim();
        String gender=genderField.getText().trim();
        String dateofbirth=dateofbirthField.getText().trim();


        if(!name.matches(PATIENT_NAME_REGEX)){
            new Alert(Alert.AlertType.ERROR,"Invalid name format!", ButtonType.OK).show();
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
                System.out.println(name +","+ address+","+nic+","+contactnumber+","+gender+","+dateofbirth);
                PatientDTO patientDTO = new PatientDTO(name , address , nic , contactnumber,gender,dateofbirth);
                boolean result = patientModel.savePatient(patientDTO);

                if(result){
                    new Alert(Alert.AlertType.INFORMATION,"Patient has been saved successfully!", ButtonType.OK).show();
                    cleanField();


                }else {
                    new Alert(Alert.AlertType.ERROR,"Failed to save patient!", ButtonType.OK).show();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void cleanField(){
        nameField.setText("");
        addressField.setText("");
        nicField.setText("");
        contactnumberField.setText("");
        genderField.setText("");
        dateofbirthField.setText("");

    }
}
