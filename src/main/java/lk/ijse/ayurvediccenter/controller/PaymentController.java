package lk.ijse.ayurvediccenter.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.ayurvediccenter.dao.custom.impl.AppointmentDAOImpl;
import lk.ijse.ayurvediccenter.dao.custom.impl.TreatmentDAOImpl;
import lk.ijse.ayurvediccenter.dto.TreatmentDTO;
import lk.ijse.ayurvediccenter.dto.tm.MedBillTM;
import lk.ijse.ayurvediccenter.model.PresMedicationModel;
import lk.ijse.ayurvediccenter.model.AppointmentModel;
import lk.ijse.ayurvediccenter.model.PrescriptionModel;
import lk.ijse.ayurvediccenter.model.TreatmentModel;

import java.util.List;

public class PaymentController {

    @FXML private TableColumn<MedBillTM ,Double> colMedCharges;
    @FXML private TableColumn<?, ?> colMedId;
    @FXML private TableColumn<?, ?> colMedName;
    @FXML private TableColumn<?, ?> colMedQty;
    @FXML private TableColumn<?, ?> colMedUnitPrice;

    @FXML private TableColumn<? ,?> colTCharges;
    @FXML private TableColumn<?, ?> colTId;
    @FXML private TableColumn<?, ?> colTName;
    @FXML private Label docChargesField;
    @FXML private Label medicineTotalField;
    @FXML private Button saveButton;
    @FXML private TableView<MedBillTM> tableMedicine;
    @FXML private TableView<TreatmentDTO> tableTreatment;
    @FXML private Label totalChargesField;
    @FXML private Label treatmentTotalField;

    private String patientId;
    private int appId;
    PresMedicationModel appMedicationModel = new PresMedicationModel();
    TreatmentModel treatmentModel = new TreatmentModel();
    AppointmentModel  appointmentModel = new AppointmentModel();
    PrescriptionModel prescriptionModel = new PrescriptionModel();

    TreatmentDAOImpl treatmentDAOImpl = new TreatmentDAOImpl();
    AppointmentDAOImpl appointmentDAOImpl = new AppointmentDAOImpl();

    double totalDocCharges;
    double totalMedicineTotal;
    double totalTreatmentTotal;
    double totalCharges;

    public void initData(String patientId ,int appId)  {
        this.patientId = patientId;
        this.appId = appId;
        System.out.println("patientId:"+patientId);

        colMedId.setCellValueFactory(new PropertyValueFactory<>("med_id"));
        colMedName.setCellValueFactory(new PropertyValueFactory<>("med_name"));
        colMedUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colMedQty.setCellValueFactory(new PropertyValueFactory<>("medicine_qty"));
        colMedCharges.setCellValueFactory(cellData -> {
            MedBillTM med = cellData.getValue();
            if (med != null) {
                double total = med.getUnit_price() * med.getMedicine_qty();
                return new SimpleDoubleProperty(total).asObject();
            }
            return null;
        });


        colTId.setCellValueFactory(new PropertyValueFactory<>("treatment_id"));
        colTName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTCharges.setCellValueFactory(new PropertyValueFactory<>("price"));



        loadTreatmentTable();
        loadMedTable();
        loadDocChargesField();
        loadTotalMedCharges();
        loadTotalTreatmentCharges();
        totalCharges();
    }

    public void loadMedTable() {
        try {
            List<MedBillTM> medicationHistoryList = appMedicationModel.getMedPrice(patientId , appId);
            ObservableList<MedBillTM> obList = FXCollections.observableArrayList(medicationHistoryList);
            tableMedicine.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTreatmentTable(){
        try{
            TreatmentDTO tList =treatmentDAOImpl.getTreatmentById(patientId,appId);
            ObservableList<TreatmentDTO> obList = FXCollections.observableArrayList(tList);
            tableTreatment.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDocChargesField(){
        try{
            totalDocCharges = appointmentDAOImpl.getDocCharges( Integer.parseInt(patientId),appId);
            System.out.println(totalDocCharges);
            docChargesField.setText(String.valueOf(totalDocCharges));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTotalMedCharges() {
        totalMedicineTotal = 0.0;

        for (MedBillTM med : tableMedicine.getItems()) {
            if (med != null) {
                // safer: use the calculation or cell value
                Double colValue = colMedCharges.getCellObservableValue(med).getValue();
                if (colValue != null) {
                    totalMedicineTotal += colValue;
                }
            }
        }

        medicineTotalField.setText(String.format("%.2f", totalMedicineTotal));
    }


    private void loadTotalTreatmentCharges() {
        ObservableList<TreatmentDTO> treatmentList = FXCollections.observableArrayList();
        tableTreatment.setItems(treatmentList);

        totalTreatmentTotal  = 0.0;
        for (TreatmentDTO treatment : tableTreatment.getItems()) {
            totalTreatmentTotal += treatment.getPrice();
        }
        treatmentTotalField .setText(String.format("%.2f", totalTreatmentTotal));
    }

    public void totalCharges(){
        totalCharges = totalDocCharges +totalMedicineTotal + totalTreatmentTotal;
        totalChargesField.setText(String.format("%.2f", totalCharges));
    }

    public void collectCharges(){
        try{
            boolean chgCollected = appointmentModel.chgsCollect(appId ,totalDocCharges ,totalMedicineTotal ,totalTreatmentTotal ,totalCharges);

            if(chgCollected){
                new Alert(Alert.AlertType.INFORMATION," Charges Collected successfully").show();
            }else {
                new Alert(Alert.AlertType.INFORMATION," Charges Collect failed").show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printBill(){
        try{
            prescriptionModel.getBill(appId);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!", ButtonType.OK).show();
        }
    }

}
