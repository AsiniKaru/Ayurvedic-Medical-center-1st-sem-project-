package lk.ijse.ayurvediccenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.ayurvediccenter.dto.PatientDTO;
import lk.ijse.ayurvediccenter.model.PatientModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PatientDashboardController implements Initializable {

    @FXML
    private TableView tablePatient;
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

    PatientModel patientModel = new PatientModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Patient FXML is loaded!");

        colId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContactnum.setCellValueFactory(new PropertyValueFactory<>("contact"));

        loadPatientTable();
    }

    private void loadPatientTable(){
        try{
            List<PatientDTO> patientList = patientModel.getPatients();

            ObservableList<PatientDTO> obList = FXCollections.observableArrayList();

            for(PatientDTO patientDTO : patientList){
                obList.add(patientDTO);
            }
            tablePatient.setItems(obList);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onActionSearch(){}

}
