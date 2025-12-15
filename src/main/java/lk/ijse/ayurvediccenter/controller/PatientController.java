package lk.ijse.ayurvediccenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


import java.awt.*;
import java.awt.event.KeyEvent;

public class PatientController {

    @FXML
    private BorderPane patientMainContent;

    @FXML
    private TextArea idField;

    @FXML
    private TextArea nameField;

    @FXML
    public void addNewpatientButton(){
        navigateTo("/lk/ijse/ayurvediccenter/view/AddNewPatient.fxml");
    }

    @FXML
    public void onActionPatientDashboard(ActionEvent actionEvent) {
        navigateTo("/lk/ijse/ayurvediccenter/view/PatientDashboard.fxml");
    }

    @FXML
    public void onActionAddNewPatient(ActionEvent actionEvent) {
        navigateTo("/lk/ijse/ayurvediccenter/view/AddNewPatient.fxml");
    }

    @FXML
    public void onActionEditPatient(ActionEvent actionEvent) {
        navigateTo("/lk/ijse/ayurvediccenter/view/EditPatient.fxml");
    }

    @FXML
    public void navigateTo(String path) {
        try {
            patientMainContent.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            anchorPane.prefWidthProperty().bind(patientMainContent.widthProperty());
            anchorPane.prefHeightProperty().bind(patientMainContent.heightProperty());
            patientMainContent.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }


}
