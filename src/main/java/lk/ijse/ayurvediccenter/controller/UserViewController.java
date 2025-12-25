package lk.ijse.ayurvediccenter.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

    @FXML
    public AnchorPane mainContent;
    @FXML
    public BorderPane ancUserView;

    @FXML
    private Label lblDayField;
    @FXML
    private Label lblDate;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblDate.setText(LocalDate.now().toString());
        lblDayField.setText(LocalDate.now().getDayOfWeek().toString());




    }



    @FXML
    public void onActionMedicine(ActionEvent actionEvent) {
        navigateTo("/lk/ijse/ayurvediccenter/view/Medicine.fxml");

    }

    @FXML
    public void onActionPatients(ActionEvent actionEvent) {
        System.out.println("PatientDashboard");
        navigateTo("/lk/ijse/ayurvediccenter/view/PatientDashboard.fxml");

    }

    @FXML
    public void onActionReports(ActionEvent actionEvent) {
        navigateTo("/lk/ijse/ayurvediccenter/view/Report.fxml");

    }

    @FXML
    public void onActionDashboard(ActionEvent actionEvent) {
        navigateTo("/lk/ijse/ayurvediccenter/view/Dashboard.fxml");

    }

    @FXML
    public void onActionAppointment(ActionEvent actionEvent) {
        navigateTo("/lk/ijse/ayurvediccenter/view/Appointment.fxml");

    }

    @FXML
    public void onActionTreatments(ActionEvent actionEvent) {
        navigateTo("/lk/ijse/ayurvediccenter/view/Treatment.fxml");

    }
    @FXML
    public  void onActionSetting(ActionEvent actionEvent) {
        try {
            ancUserView.getChildren().clear();
            BorderPane borderPane = (BorderPane) FXMLLoader.load(getClass().getResource("/lk/ijse/ayurvediccenter/view/Setting.fxml"));
            borderPane.prefWidthProperty().bind(ancUserView.widthProperty());
            borderPane.prefHeightProperty().bind(ancUserView.heightProperty());
            ancUserView.getChildren().add(borderPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }

    }

    @FXML
    public void onActionLogout(ActionEvent actionEvent) {

        try {
            ancUserView.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/ayurvediccenter/view/Login.fxml"));
            anchorPane.prefWidthProperty().bind(ancUserView.widthProperty());
            anchorPane.prefHeightProperty().bind(ancUserView.heightProperty());
            ancUserView.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }


    @FXML
    public void navigateTo(String path) {
        try {
            mainContent.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            anchorPane.prefWidthProperty().bind(mainContent.widthProperty());
            anchorPane.prefHeightProperty().bind(mainContent.heightProperty());
            mainContent.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }
}

