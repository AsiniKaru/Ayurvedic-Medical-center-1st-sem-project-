package lk.ijse.ayurvediccenter.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.model.AppointmentModel;
import lk.ijse.ayurvediccenter.model.MedicineModel;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

    @FXML public AnchorPane mainContent;

    @FXML public BorderPane ancUserView;

    @FXML private Label lblDayField;

    @FXML private Label lblDate;

    @FXML private Label medQtyField;

    @FXML private Label lowMedField;

    @FXML private Label activeAppField;

    @FXML private Label totalNumAppField;

    @FXML private TableView<AppPatientTM> tableAppList;
    @FXML private TableColumn<AppPatientTM, Integer> colAppId;
    @FXML private TableColumn<AppPatientTM, String> colPName;
    @FXML private BarChart<String, Number> barChart;


    MedicineModel  medicineModel = new MedicineModel();
    AppointmentModel appointmentModel = new AppointmentModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblDate.setText(LocalDate.now().toString());
        lblDayField.setText(LocalDate.now().getDayOfWeek().toString());
        dashboardMedQty();
        dashboardLowMedQty();
        dashboardAppNum();
        dashboardActiveAppNum();

        colAppId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("patientName"));

        loadTodayAppointmentTable();
        loadChart();

    }

    public void dashboardAppNum(){
        try{
            String activeApp = String.valueOf(appointmentModel.getNumOfApp());
            totalNumAppField.setText("Total num of Appointment : "+ activeApp);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void dashboardActiveAppNum(){
        try{
            String activeApp = String.valueOf(appointmentModel.getNumOfActiveApp());
            activeAppField.setText(activeApp);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void dashboardLowMedQty() {
        try{
            String medQty =String.valueOf(medicineModel.getLowMedQty());
            lowMedField.setText("Low Med stock: "+ medQty +" items");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void dashboardMedQty() {
        try {
            String medQty = String.valueOf(medicineModel.getTotalMedQty());
            medQtyField.setText(medQty);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadTodayAppointmentTable(){
        try{
            List<AppPatientTM> appointmentList = appointmentModel.getTodayAppointments();

            ObservableList<AppPatientTM> obList = FXCollections.observableArrayList();

            for(AppPatientTM appointmentDTO : appointmentList){
                obList.add(appointmentDTO);
            }
            tableAppList.setItems(obList);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void loadChart() {
        try {
            ResultSet rs = CrudUtil.execute(
                    "SELECT appointment_date, SUM(total_charges) AS revenue " +
                            "FROM Appointment GROUP BY appointment_date"
            );

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Daily Revenue");

            while (rs.next()) {
                series.getData().add(
                        new XYChart.Data<>(
                                rs.getString("appointment_date"),
                                rs.getDouble("revenue")
                        )
                );
            }

            barChart.getData().add(series);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onActonAppointment(ActionEvent actionEvent) {
        navigateTo("/lk/ijse/ayurvediccenter/view/Appointment.fxml");

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

