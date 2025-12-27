package lk.ijse.ayurvediccenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.model.AppointmentModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentController  implements Initializable {

    @FXML private TableColumn<AppPatientTM, Void> colAction;

    @FXML private TableColumn<AppPatientTM, String> colAppDate;

    @FXML private TableColumn<AppPatientTM, Integer> colAppId;

    @FXML private TableColumn<AppPatientTM, String> colAppStatus;

    @FXML private TableColumn<AppPatientTM,String> colContactNum;

    @FXML private TableColumn<AppPatientTM,String> colName;

    @FXML private TableColumn<AppPatientTM, Integer>  colPId;

    @FXML private TableColumn<AppPatientTM, Integer>  colAppType;

    @FXML private TableView<AppPatientTM> tableAppointment;

    @FXML private DatePicker searchByDateField;

    @FXML private TextArea searchByNameOrIdField;

    AppointmentModel appointmentModel = new AppointmentModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Appointment FXML is loaded!");

        colAppId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colPId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colAppDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        colName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colContactNum.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAppType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        colAppStatus.setCellValueFactory(new PropertyValueFactory<>("appointmentStatus"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("appId"));


        loadTodayAppointmentTable();

    }

    @FXML
    private void onActionAddAppointment() {
      try{

          FXMLLoader loader = new FXMLLoader();

          loader.setLocation(getClass().getResource("/lk/ijse/ayurvediccenter/view/AddAppointment.fxml"));

          Parent root = loader.load();

          AddAppointmentController addController = loader.getController();
          addController.setAppointmentController(this);


          Stage newStage = new Stage();
          newStage.setTitle("Add New Appointment");
          newStage.setScene(new Scene(root));

          newStage.initModality(Modality.APPLICATION_MODAL);

          newStage.show();

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
            tableAppointment.setItems(obList);

        }catch(Exception e){
            e.printStackTrace();
        }
    }





}