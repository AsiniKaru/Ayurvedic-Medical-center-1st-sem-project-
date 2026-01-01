package lk.ijse.ayurvediccenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.ayurvediccenter.dto.tm.AppPrescriptionTM;
import lk.ijse.ayurvediccenter.model.AppPrescriptionModel;

import java.util.List;


public class DiagnosisViewController {

    @FXML
    private TableColumn<?, ?> colAppDate;
    @FXML
    private TableColumn<?, ?> colAppId;
    @FXML
    private TableColumn<?, ?> colDiagnosisNote;
    @FXML
    private TableView<AppPrescriptionTM> tableDiagnosisHistory;

    private int patientId;

    AppPrescriptionModel appPrescriptionModel = new AppPrescriptionModel();

    public void setPatientId(int patientId) {
        this.patientId = patientId;

        colAppId.setCellValueFactory(new PropertyValueFactory<>("appId"));
        colAppDate.setCellValueFactory(new PropertyValueFactory<>("appDate"));
        colDiagnosisNote.setCellValueFactory(new PropertyValueFactory<>("diagnosisDetails"));

        loadDiagnosisHistory();
    }


    @FXML
    public void loadDiagnosisHistory(){
        try{
            List<AppPrescriptionTM> diagnosisHistoryList = appPrescriptionModel.getDiagnosisHistory(patientId);

            ObservableList<AppPrescriptionTM> obList = FXCollections.observableArrayList();

            for(AppPrescriptionTM appPrescriptionTM : diagnosisHistoryList){
                obList.add(appPrescriptionTM);
            }
            tableDiagnosisHistory.setItems(obList);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
//
//    private int appId;
//
//    PrescriptionModel prescriptionModel =  new PrescriptionModel();
//
//    public void initData(int appId) {
//        this.appId = appId;
////        loadDiagnosisHistory();
//
//        colAppId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
//        colDiagnosisNote.setCellValueFactory(new PropertyValueFactory<>("diagnosisDetails"));
//    }}

//    public void loadDiagnosisHistory() {
//
//        try{
//            List<PrescriptionDTO> prescriptionList = prescriptionModel.getPrescription(appId);
//
//            ObservableList<PrescriptionDTO> obList = FXCollections.observableArrayList();
//
//            for(PrescriptionDTO prescriptionDTO : prescriptionList){
//                obList.add(prescriptionDTO);
//            }
//            tableDiagnosisHistory.setItems(obList);
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//}
}