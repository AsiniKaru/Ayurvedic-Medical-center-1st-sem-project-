package lk.ijse.ayurvediccenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AppointmentController {

    @FXML
    private TableView tableAppointment;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colAddress;
    @FXML
    private TableColumn colNic;
    @FXML
    private TableColumn colContactnum;
    @FXML
    private TableColumn conLastVisitDay;

    @FXML
    private void onActionAddAppointment(ActionEvent event) {
        System.out.println("Add Appointment clicked");
    }
}
