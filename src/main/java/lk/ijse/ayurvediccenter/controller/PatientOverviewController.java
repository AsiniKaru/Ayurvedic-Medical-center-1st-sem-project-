package lk.ijse.ayurvediccenter.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.PatientDTO;
import lk.ijse.ayurvediccenter.model.PatientModel;

import java.util.Optional;

public class PatientOverviewController {

    @FXML
    public AnchorPane overviewContent;
    @FXML
    public AnchorPane profileContent;

    @FXML
    private Label addressField;

    @FXML
    private Label contactField;

    @FXML
    private Label dateOfBirthField;

    @FXML
    private Label fNameField;

    @FXML
    private Label genderField;

    @FXML
    private Label idField;

    @FXML
    private Label lNameField;

    @FXML
    private Label nicField;

    @FXML
    private Label pSinceField;

    @FXML
    private Button deleteButton;

    private final PatientModel patientModel = new PatientModel();

    private PatientController patientController = new PatientController();

    private PatientDTO currentDTO;

    public void initData(PatientDTO patientDTO) {

        System.out.println("patientDTO: " + patientDTO);
        idField.setText(String.valueOf(patientDTO.getPatientId()));
        fNameField.setText(patientDTO.getFirstName());
        lNameField.setText(patientDTO.getLastName());
        addressField.setText(patientDTO.getAddress());
        nicField.setText(patientDTO.getNic());
        genderField.setText(patientDTO.getGender());
        contactField.setText(patientDTO.getContact());
        dateOfBirthField.setText(String.valueOf(patientDTO.getDateOfBirth()));
        pSinceField.setText(String.valueOf(patientDTO.getPatientSince()));

       this.currentDTO = patientDTO;
    }

    @FXML
    public void onActionOverview(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/lk/ijse/ayurvediccenter/view/PatientProfile.fxml")
            );

            AnchorPane pane = loader.load();

            // get REAL controller created by JavaFX
            PatientProfileController controller = loader.getController();

            // pass currentDTO
            controller.initData(currentDTO);

            profileContent.getChildren().clear();
            profileContent.getChildren().add(pane);

            pane.prefWidthProperty().bind(profileContent.widthProperty());
            pane.prefHeightProperty().bind(profileContent.heightProperty());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onActionDiagnosisHistory(ActionEvent actionEvent) {
        System.out.println("Patient Profile diagnosis H Clicked");
        navigateTo("/lk/ijse/ayurvediccenter/view/DiagnosisView.fxml");
    }

    @FXML
    public void onActionMedicationHistory(ActionEvent actionEvent) {
        System.out.println("Patient Profile diagnosis H Clicked");
        navigateTo("/lk/ijse/ayurvediccenter/view/MedicationView.fxml");
    }

    @FXML
    public void onActionEditPatient(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/lk/ijse/ayurvediccenter/view/EditPatient.fxml")
            );

            AnchorPane pane = loader.load(); // IMPORTANT

            EditPatientController controller = loader.getController();
            controller.initData(currentDTO); // patientDTO already exists

            profileContent.getChildren().clear();
            profileContent.getChildren().add(pane);

            pane.prefWidthProperty().bind(profileContent.widthProperty());
            pane.prefHeightProperty().bind(profileContent.heightProperty());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onActionDeletePatient(ActionEvent actionEvent) {
        try {

            boolean isDeleted = patientModel.deletePatient(String.valueOf(currentDTO.getPatientId()));

            if(isDeleted) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Patient Record Deleted Successfully");

                ButtonType buttonYes = new ButtonType(" Go Back");
                alert.getButtonTypes().setAll(buttonYes);
                Optional<ButtonType> results = alert.showAndWait();
                patientController.loadPatientTable();


                if (results.isPresent() && results.get() == buttonYes) {
                    Stage currentStage = (Stage) deleteButton.getScene().getWindow();
                    currentStage.close();

                }


            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }

        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }

    }
/*

medicineController.loadMedicineTable();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success!");
                                alert.setHeaderText("Medicine successfully Updated");

                                ButtonType buttonYes = new ButtonType("update Again");
                                ButtonType buttonNo = new ButtonType("Go Back ");


                                Optional<ButtonType> results = alert.showAndWait();


                                if (results.isPresent() && results.get() == buttonYes) {


                                } else {


                                }

 */

    @FXML
    public void navigateTo(String path) {
        try {
            profileContent.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            anchorPane.prefWidthProperty().bind(profileContent.widthProperty());
            anchorPane.prefHeightProperty().bind(profileContent.heightProperty());
            profileContent.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }
}