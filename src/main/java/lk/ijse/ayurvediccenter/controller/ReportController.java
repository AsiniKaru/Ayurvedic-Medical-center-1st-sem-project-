package lk.ijse.ayurvediccenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import lk.ijse.ayurvediccenter.util.report.Report;

public class ReportController {
    @FXML
    private Button btngenerateTSR;

    @FXML
    private DatePicker dateField;

    Report reportModel = new Report();

    @FXML
    private void generateTSR(ActionEvent event) {
        try{
            String date = dateField.getValue().toString();
            reportModel.printTSReport(date);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!", ButtonType.OK).show();
        }
    }


}
