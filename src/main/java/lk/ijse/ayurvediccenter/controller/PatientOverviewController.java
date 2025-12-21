package lk.ijse.ayurvediccenter.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.ayurvediccenter.dto.PatientDTO;

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