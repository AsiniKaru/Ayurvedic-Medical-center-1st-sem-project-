package lk.ijse.ayurvediccenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.ayurvediccenter.dto.PrescriptionDTO;
import lk.ijse.ayurvediccenter.dto.PrescriptionMedDTO;
import lk.ijse.ayurvediccenter.dto.tm.AppMedicationTM;
import lk.ijse.ayurvediccenter.model.AppMedicationModel;
import lk.ijse.ayurvediccenter.model.PrescriptionModel;

import java.util.List;

public class MedicationViewController {

    @FXML private TableColumn<?, ?> colAppDate;
    @FXML private TableColumn<?, ?> colAppId;
    @FXML private TableColumn<?, ?> colMedInstruction;
    @FXML private TableColumn<?, ?> colMedName;
    @FXML private TableColumn<?, ?> colMedQty;
    @FXML private TableView<AppMedicationTM> tableMedHistory;

    private int patientId;

    AppMedicationModel appMedicationModel =  new AppMedicationModel();

    public void initData(int patientId) {
        this.patientId = patientId;

        colAppDate.setCellValueFactory(new PropertyValueFactory<>("appDate"));
        colAppId.setCellValueFactory(new PropertyValueFactory<>("appId"));
        colMedName.setCellValueFactory(new PropertyValueFactory<>("medName"));
        colMedQty.setCellValueFactory(new PropertyValueFactory<>("medQty"));
        colMedInstruction.setCellValueFactory(new PropertyValueFactory<>("medInstruction"));

        loadMedicationHistory();

    }

    public void loadMedicationHistory() {

        try{

            List<AppMedicationTM> medicationHistoryList = appMedicationModel.getMedHistory(patientId);

            ObservableList<AppMedicationTM> obList = FXCollections.observableArrayList();

            for(AppMedicationTM appMedicationTM : medicationHistoryList){
                obList.add(appMedicationTM);
            }
            tableMedHistory.setItems(obList);

        }catch(Exception e){
            e.printStackTrace();
        }

    }


}
