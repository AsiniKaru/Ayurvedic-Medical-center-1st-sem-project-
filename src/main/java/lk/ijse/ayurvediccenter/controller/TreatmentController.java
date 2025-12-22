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
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.TreatmentDTO;
import lk.ijse.ayurvediccenter.model.TreatmentModel;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TreatmentController implements Initializable {

    @FXML
    private TableColumn colDesc;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colType;

    @FXML
    private TableColumn colAction;

    @FXML
    private TableView tableTreatment;

    @FXML
    private TextField tIdField;
    @FXML
    private TextField tNameField;


    TreatmentModel treatmentModel = new TreatmentModel();

    private final String TREATMENT_ID_REGEX = "^[0-9]+$";
    private final String TREATMENT_NAME_REGEX = "^[A-Za-z]+ [A-Za-z]+$";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Treatment FXML is loaded!");

        colId.setCellValueFactory(new PropertyValueFactory<>("treatment_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));



        // Custom cell factory for description to wrap text
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDesc.setCellFactory(tc -> {
            return new TableCell<TreatmentDTO, String>() {
                private final TextArea textArea = new TextArea();

                {
                    textArea.setWrapText(true);        // wrap text
                    textArea.setEditable(false);       // read-only
                    textArea.setPrefHeight(10);       // initial row height
                    textArea.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-border-color: transparent;");
                }

                @Override
                protected void updateItem(String description, boolean empty) {
                    super.updateItem(description, empty);
                    if (empty || description == null) {
                        setGraphic(null);
                    } else {
                        textArea.setText(description);
                        setGraphic(textArea);

                        // Adjust row height based on text
                        this.setPrefHeight(Math.max(50, textArea.getText().split("\n").length * 20));
                    }
                }
            };
        });

        // Optional: fixed row height for other rows
        tableTreatment.setFixedCellSize(70);
        tableTreatment.setStyle("-fx-font-size: 12;");


        loadTreatmentTable();
//
//        tableTreatment.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//                try {
//                    openPatientOverview(newSelection);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }



    @FXML
    public void loadTreatmentTable(){
        try{
            List<TreatmentDTO> treatmentList = treatmentModel.getTreatments();

            ObservableList<TreatmentDTO> obList = FXCollections.observableArrayList();

            for(TreatmentDTO treatmentDTO : treatmentList){
                obList.add(treatmentDTO);
            }
            tableTreatment.setItems(obList);

        }catch(Exception e){
            e.printStackTrace();
        }
    }


/*
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

*/

    @FXML
    public void onActionAddTreatment(){
        try{

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/lk/ijse/ayurvediccenter/view/AddTreatment.fxml"));

            Parent root = loader.load();

            AddTreatmentController addController = loader.getController();
            addController.setTreatmentController(this);


            Stage newStage = new Stage();
            newStage.setTitle("Add New Treatment");
            newStage.setScene(new Scene(root));

            newStage.initModality(Modality.APPLICATION_MODAL);

            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEditTreatment(TreatmentDTO treatmentDTO){}

    @FXML
    public void handleDeleteTreatment(TreatmentDTO treatmentDTO){}


    @FXML
    private void handleSearchById(KeyEvent event){
        try{
            if(event.getCode()== KeyCode.ENTER){
                String id = tIdField.getText();

                if(!id.matches(TREATMENT_ID_REGEX)){
                    new Alert(Alert.AlertType.ERROR , "Invalid Treatment ID!").show();


                }else {
                    TreatmentDTO treatmentDTO = treatmentModel.searchTreatment(id);

                    if(treatmentDTO != null){

                        ObservableList<TreatmentDTO> obList =
                                FXCollections.observableArrayList(treatmentDTO);
                        tableTreatment.setItems(obList);

                        clearFields();


                    }else{
                        new Alert(Alert.AlertType.ERROR , "Treatment ID not found!").show();
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
                String name = tNameField.getText();

                if(!name.matches(TREATMENT_NAME_REGEX)){
                    new Alert(Alert.AlertType.ERROR , "Invalid Treatment Name!").show();


                }else {
                    TreatmentDTO treatmentDTO = treatmentModel.searchTreatmentByName(name);

                    if(treatmentDTO != null){

                        ObservableList<TreatmentDTO> obList =
                                FXCollections.observableArrayList(treatmentDTO);
                        tableTreatment.setItems(obList);

                        clearFields();


                    }else{
                        new Alert(Alert.AlertType.ERROR , "Treatment not found!").show();
                        clearFields();

                    }

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void clearFields() {
        tIdField.clear();
        tNameField.clear();
    }


}
