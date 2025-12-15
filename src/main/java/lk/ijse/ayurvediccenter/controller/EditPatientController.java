package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.ayurvediccenter.dto.PatientDTO;
import lk.ijse.ayurvediccenter.model.PatientModel;

public class EditPatientController {



    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea addressField;

    @FXML
    private TextField nicField;

    @FXML
    public TextField contactNumberField;

    @FXML
    private TextField genderField;

    @FXML
    public TextField dateofbirthField;


    private final String PATIENT_ID_REGEX = "^[0-9]+$";
    private final String PATIENT_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_ADDRESS_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_NIC_REGEX = "^([0-9]{9}[Vv]|[0-9]{12})$";
    private final String PATIENT_CONTACT_REGEX = "^[0-9]{10}$";
    private final String PATIENT_GENDER_REGEX = "(?i)^(male|female)$";
    private final String PATIENT_DATE_OF_BIRTH_REGEX = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

    PatientModel patientModel = new PatientModel();


    @FXML
    private void handleSearchPatient(KeyEvent event){
        try{
            if(event.getCode()== KeyCode.ENTER){
                String id = idField.getText();

                if(!id.matches(PATIENT_ID_REGEX)){
                    new Alert(Alert.AlertType.ERROR , "Invalid Patient ID!").show();
                }else {
                    PatientDTO patientDTO = patientModel.searchPatient(id);

                    if(patientDTO != null){

                        nameField.setText(patientDTO.getPatient_name());
                        addressField.setText(patientDTO.getAddress());
                        nicField.setText(patientDTO.getPatient_nic());
                        contactNumberField.setText(patientDTO.getContact_num());
                        genderField.setText(patientDTO.getGender());
                        dateofbirthField.setText(patientDTO.getDate_of_birth());

                    }else{
                        new Alert(Alert.AlertType.ERROR , "Patient not found!").show();
                    }

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void handleUpdatePatient() {
        try{
            String id = idField.getText();
            String name = nameField.getText().trim();
            String address = addressField.getText().trim();
            String nic = nicField.getText().trim();
            String contactnumber = contactNumberField.getText().trim();
            String gender = genderField.getText().trim();
            String dateofbirth = dateofbirthField.getText().trim();

            if (!id.matches(PATIENT_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid id format!", ButtonType.OK).show();
            } else if (!name.matches(PATIENT_NAME_REGEX)) {
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
                System.out.println(name + "," + address + "," + nic + "," + contactnumber + "," + gender + "," + dateofbirth);
                PatientDTO patientDTO = new PatientDTO(Integer.parseInt(id), name, address, nic, contactnumber, gender, dateofbirth);

                boolean result = patientModel.updatePatient(patientDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Patient has been Updated successfully!", ButtonType.OK).show();
                    cleanField();


                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update patient!", ButtonType.OK).show();
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

        @FXML
        private void cleanField () {
            idField.setText("");
            nameField.setText("");
            addressField.setText("");
            nicField.setText("");
            contactNumberField.setText("");
            genderField.setText("");
            dateofbirthField.setText("");

        }

}