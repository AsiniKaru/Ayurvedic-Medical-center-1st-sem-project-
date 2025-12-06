package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Label lblDayField;

    @FXML
    private Label lblDate;

    @FXML
    private BorderPane dashBoardContent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblDate.setText(LocalDate.now().toString());
        lblDayField.setText(LocalDate.now().getDayOfWeek().toString());
    }
}
