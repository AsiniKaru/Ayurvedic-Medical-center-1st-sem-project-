package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.ayurvediccenter.dto.PatientDTO;
import lk.ijse.ayurvediccenter.model.PatientModel;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class EditPatientController {

    @FXML
    private TextField addressField;

    @FXML
    private TextField contactnumField;

    @FXML
    private TextField dateOfBirthField;

    @FXML
    private TextField fNameField;

    @FXML
    private TextField genderField;

    @FXML
    private Label idField;

    @FXML
    private TextField lNameField;

    @FXML
    private TextField nicField;

    @FXML
    private Label sinceField;


    private final String PATIENT_ID_REGEX = "^[0-9]+$";
    private final String PATIENT_FIRST_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_LAST_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_ADDRESS_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_NIC_REGEX = "^([0-9]{9}[Vv]|[0-9]{12})$";
    private final String PATIENT_CONTACT_REGEX = "^[0-9]{10}$";
    private final String PATIENT_GENDER_REGEX = "(?i)^(male|female)$";
    private final String PATIENT_DATE_OF_BIRTH_REGEX = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

    PatientModel patientModel = new PatientModel();

    private PatientDTO patientDTO;

    public void initData(PatientDTO patientDTO) {
        System.out.println(patientDTO);

        this.patientDTO = patientDTO;

        idField.setText(String.valueOf(patientDTO.getPatientId()));
        fNameField.setText(patientDTO.getFirstName());
        lNameField.setText(patientDTO.getLastName());
        addressField.setText(patientDTO.getAddress());
        nicField.setText(patientDTO.getNic());
        genderField.setText(patientDTO.getGender());
        dateOfBirthField.setText(String.valueOf(patientDTO.getDateOfBirth()));
        contactnumField.setText(patientDTO.getContact());
        sinceField.setText(String.valueOf(patientDTO.getPatientSince()));
    }

//        @FXML
//    public void handleSearchPatient(KeyEvent event){
//        try{
//            if(event.getCode()== KeyCode.ENTER){
//                String id = idField.getText();
//
//                if(!id.matches(PATIENT_ID_REGEX)){
//                    new Alert(Alert.AlertType.ERROR , "Invalid Patient ID!").show();
//                }else {
//                    PatientDTO patientDTO = patientModel.searchPatient(id);
//
//
//                    if(patientDTO != null){
//
//                        idField.setText(String.valueOf(patientDTO.getPatientId()));
//                        fNameField.setText(patientDTO.getFirstName());
//                        lNameField.setText(patientDTO.getLastName());
//                        addressField.setText(patientDTO.getAddress());
//                        nicField.setText(patientDTO.getNic());
//                        genderField.setText(patientDTO.getGender());
//                        contactnumField.setText(patientDTO.getContact());
//                        dateOfBirthField.setText(patientDTO.getDateOfBirth());
//                        sinceField.setText(patientDTO.getPatientSince());
//
//
//
//                    }else{
//                        new Alert(Alert.AlertType.ERROR , "Patient not found!").show();
//                    }
//
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//    }


    @FXML
    private void handleUpdatePatient() {
        try {
            String id = idField.getText();
            String fName = fNameField.getText().trim();
            String lName = lNameField.getText().trim();
            String address = addressField.getText().trim();
            String nic = nicField.getText().trim();
            String contactnumber = contactnumField.getText().trim();
            String gender = genderField.getText().trim();
            String dateofbirth = dateOfBirthField.getText().trim();

            if (!id.matches(PATIENT_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid id format!", ButtonType.OK).show();
            } else if (!fName.matches(PATIENT_FIRST_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid name format!", ButtonType.OK).show();
            } else if (!lName.matches(PATIENT_LAST_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid name format!", ButtonType.OK).show();
            } else if (!address.matches(PATIENT_ADDRESS_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid address format!", ButtonType.OK).show();
            } else if (!nic.matches(PATIENT_NIC_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid nic format!", ButtonType.OK).show();
            } else if (!contactnumber.matches(PATIENT_CONTACT_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid contact number format!", ButtonType.OK).show();
            } else if (!dateofbirth.matches(PATIENT_DATE_OF_BIRTH_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid date format!", ButtonType.OK).show();
            } else if (!gender.matches(PATIENT_GENDER_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid gender format!", ButtonType.OK).show();
            } else {
                System.out.println(fName + "," + lName + "," + address + "," + nic + "," + contactnumber + "," + gender + "," + dateofbirth);
                PatientDTO patientDTO = new PatientDTO(Integer.parseInt(id), fName, lName, address, nic, contactnumber, gender, dateofbirth);

                boolean result = patientModel.updatePatient(patientDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Patient has been Updated successfully!", ButtonType.OK).show();


                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update patient!", ButtonType.OK).show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onActionReset(ActionEvent event) {
        initData(patientDTO);

    }



}