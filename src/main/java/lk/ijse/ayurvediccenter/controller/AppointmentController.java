package lk.ijse.ayurvediccenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AppointmentController {

    @FXML
    private TableColumn colAppId;

    @FXML
    private TableColumn colContactnum;

    @FXML
    private TableColumn colFName;

    @FXML
    private TableColumn colLName;

    @FXML
    private TableColumn colPId;

    @FXML
    private TableColumn conLastVisitDay;


    @FXML
    private TableView tableAppointment;


    @FXML
    private void onActionAddAppointment(ActionEvent event) {

        System.out.println("Add Appointment clicked");
    }
}
