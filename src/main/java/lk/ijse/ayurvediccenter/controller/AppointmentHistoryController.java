package lk.ijse.ayurvediccenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.dto.tm.PatientAppointmentTM;
import lk.ijse.ayurvediccenter.model.AppPrescriptionModel;

import java.util.List;


public class AppointmentHistoryController {
    @FXML private TableColumn<?, ?> colAppDate;
    @FXML private TableColumn<?, ?> colAppId;
    @FXML private TableColumn<?, ?> colAppStatus;
    @FXML private TableColumn<?, ?> colAppType;
    @FXML private TableColumn<?, ?> colMedCharges;
    @FXML private TableColumn<?, ?> colNextVisit;
    @FXML private TableColumn<?, ?> colDocCharges;
    @FXML private TableColumn<?, ?> colTotalCharges;
    @FXML private TableColumn<?, ?> colTreatmentCharges;
    @FXML private TableView<PatientAppointmentTM> tableAppHistory;

    public int patientId;

    AppPrescriptionModel  appPrescriptionModel = new AppPrescriptionModel();

    public void initData(int patientId){
        this.patientId = patientId;
        System.out.println(patientId);

        colAppId.setCellValueFactory(new PropertyValueFactory<>("appId"));
        colAppDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colAppType.setCellValueFactory(new PropertyValueFactory<>("appType"));
        colAppStatus.setCellValueFactory(new PropertyValueFactory<>("appStatus"));
        colDocCharges.setCellValueFactory(new PropertyValueFactory<>("docCharges"));
        colMedCharges.setCellValueFactory(new PropertyValueFactory<>("prescriptionCharge"));
        colTreatmentCharges.setCellValueFactory(new PropertyValueFactory<>("treatmentCharge"));
        colTotalCharges.setCellValueFactory(new PropertyValueFactory<>("totalCharge"));
        colNextVisit.setCellValueFactory(new PropertyValueFactory<>("nextVisitDate"));


        loadAppHistory();

    }

    public void loadAppHistory(){
        try{
            List<PatientAppointmentTM> appHistoryList = appPrescriptionModel.getAppHistory(patientId);

            ObservableList<PatientAppointmentTM> obList = FXCollections.observableArrayList();

            for(PatientAppointmentTM patientAppointmentTM : appHistoryList){
                obList.add(patientAppointmentTM);
            }
            tableAppHistory.setItems(obList);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
