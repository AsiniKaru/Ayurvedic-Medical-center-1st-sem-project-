package lk.ijse.ayurvediccenter.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UserViewController  {


    public AnchorPane mainContent;
    public BorderPane ancUserView;

    public void onActionMedicine(ActionEvent actionEvent) {
    }

    public void onActionPatients(ActionEvent actionEvent) {
    }

    public void onActionReports(ActionEvent actionEvent) {
    }

    public void onActionDashboard(ActionEvent actionEvent) {
        navigateTo("/lk/ijse/ayurvediccenter/view/Dashboard.fxml");

    }

    public void onActionAppointment(ActionEvent actionEvent) {
        navigateTo("/lk/ijse/ayurvediccenter/view/Appointment.fxml");

    }

    public void onActionTreatments(ActionEvent actionEvent) {
        navigateTo("/lk/ijse/ayurvediccenter/view/Login.fxml");

    }

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
        }    }



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

