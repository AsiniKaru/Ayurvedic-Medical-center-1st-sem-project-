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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.PatientDTO;
import lk.ijse.ayurvediccenter.model.PatientModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


//
public class PatientController implements Initializable {
//
//    @FXML
//    private BorderPane patientMainContent;
//
    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TableView <PatientDTO>tablePatient;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colFName;
    @FXML
    private TableColumn colAddress;
    @FXML
    private TableColumn colLName;
    @FXML
    private TableColumn colContactnum;


    private final String PATIENT_ID_REGEX = "^[0-9]+$";
    private final String PATIENT_FIRST_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_LAST_NAME_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_ADDRESS_REGEX = "^[a-zA-Z]{3,}$";
    private final String PATIENT_NIC_REGEX = "^([0-9]{9}[Vv]|[0-9]{12})$";
    private final String PATIENT_CONTACT_REGEX = "^[0-9]{10}$";
    private final String PATIENT_GENDER_REGEX = "(?i)^(male|female)$";
    private final String PATIENT_DATE_OF_BIRTH_REGEX = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";


    PatientModel patientModel = new PatientModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Patient FXML is loaded!");

        colId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactnum.setCellValueFactory(new PropertyValueFactory<>("contact"));

        loadPatientTable();

        tablePatient.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // When a row is selected, call a method to open the new window
                try {
                    // 'newSelection' is the Patient object from the selected row
                    openPatientOverview(newSelection);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }



    @FXML
    public void loadPatientTable(){
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

    private void openPatientOverview(PatientDTO selectedPatient) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/ayurvediccenter/view/PatientOverview.fxml"));
        Parent parent = loader.load();

        PatientOverviewController patientOverviewController = loader.getController();
        patientOverviewController.initData(selectedPatient);

        Stage stage = new Stage();
        stage.setTitle("Patient Details of  " + selectedPatient.getFirstName() + " " + selectedPatient.getLastName());
        stage.setScene(new Scene(parent));

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }



    @FXML
    public void onActionAddPatient(){
        try{

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/lk/ijse/ayurvediccenter/view/AddNewPatient.fxml"));

            Parent root = loader.load();

            // 4. Create the new Stage (the window container)
            Stage newStage = new Stage();
            newStage.setTitle("Add New Patient");
            newStage.setScene(new Scene(root));

            newStage.initModality(Modality.APPLICATION_MODAL); // The correct method name
            // 6. Show the window
            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearchById(KeyEvent event){
        try{
            if(event.getCode()== KeyCode.ENTER){
                String id = idField.getText();

                if(!id.matches(PATIENT_ID_REGEX)){
                    new Alert(Alert.AlertType.ERROR , "Invalid Patient ID!").show();


                }else {

                    PatientDTO patientDTO = patientModel.searchPatient(id);

                    if(patientDTO != null){

                        ObservableList<PatientDTO> obList =
                                FXCollections.observableArrayList(patientDTO);
                        tablePatient.setItems(obList);

                        clearFields();


                    }else{
                        new Alert(Alert.AlertType.ERROR , "Patient ID not found!").show();
                        clearFields();

                    }

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void handleSearchByName(KeyEvent event){
        try{
            if(event.getCode()== KeyCode.ENTER){
                String name = nameField.getText();

                if(!name.matches(PATIENT_FIRST_NAME_REGEX)){
                    new Alert(Alert.AlertType.ERROR , "Invalid Patient Name!").show();


                }else {

                    PatientDTO patientDTO = patientModel.searchPatientByName(name);

                    if(patientDTO != null){

                        ObservableList<PatientDTO> obList =
                                FXCollections.observableArrayList(patientDTO);
                        tablePatient.setItems(obList);

                        clearFields();


                    }else{
                        new Alert(Alert.AlertType.ERROR , "Patient not found!").show();
                        clearFields();

                    }

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
    }


////    @FXML
////    public void onActionPatientDashboard(ActionEvent actionEvent) {
////        navigateTo("/lk/ijse/ayurvediccenter/view/.fxml");
////    }
//
//    @FXML
//    public void onActionAddNewPatient(ActionEvent actionEvent) {
//        navigateTo("/lk/ijse/ayurvediccenter/view/AddNewPatient.fxml");
//    }
//
//    @FXML
//    public void onActionEditPatient(ActionEvent actionEvent) {
//        navigateTo("/lk/ijse/ayurvediccenter/view/EditPatient.fxml");
//    }
//
//    @FXML
//    public void navigateTo(String path) {
//        try {
//            patientMainContent.getChildren().clear();
//            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
//            anchorPane.prefWidthProperty().bind(patientMainContent.widthProperty());
//            anchorPane.prefHeightProperty().bind(patientMainContent.heightProperty());
//            patientMainContent.getChildren().add(anchorPane);
//        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
//            e.printStackTrace();
//        }
//    }
//
//
}
