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
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.MedicineDTO;
import lk.ijse.ayurvediccenter.dto.TreatmentDTO;
import lk.ijse.ayurvediccenter.model.MedicineModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MedicineController implements Initializable {

    @FXML
    private TableColumn colAction;

    @FXML
    private TableColumn colDesc;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colQty;


    @FXML
    private TextField tIdField;

    @FXML
    private TextField tNameField;

    @FXML
    private TableView tableMedicine;

    private final String MEDICINE_ID_REGEX = "^[0-9]+$";
    private final String MEDICINE_NAME_REGEX = "^[A-Za-z]+ [A-Za-z]+$";
    private final String MEDICINE_QTY_REGEX =  "^[0-9]+$";
    private final String MEDICINE_DESCRIPTION_REGEX = "^(?:(?:\\S+\\s+){0,299}\\S+)?\\s*$";
    private final String MEDICINE_PRICE_REGEX = "^\\d+(\\.\\d{1,2})?$";

    MedicineModel medicineModel = new MedicineModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Medicine FXML is loaded!");

        colId.setCellValueFactory(new PropertyValueFactory<>("med_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
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
        tableMedicine.setFixedCellSize(70);
        tableMedicine.setStyle("-fx-font-size: 12;");


        loadMedicineTable();

    }

    @FXML
    public void loadMedicineTable(){
        try{
            List<MedicineDTO> medicineList = medicineModel.getMedicine();

            ObservableList<MedicineDTO> obList = FXCollections.observableArrayList();

            for(MedicineDTO medicinetDTO : medicineList){
                obList.add(medicinetDTO);
            }
            tableMedicine.setItems(obList);

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    public void onActionAddMedicine(){
        try{

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/lk/ijse/ayurvediccenter/view/AddMedicine.fxml"));

            Parent root = loader.load();

            AddMedicineController addController = loader.getController();
            addController.setMedicineController(this);


            Stage newStage = new Stage();
            newStage.setTitle("Add New Medicine");
            newStage.setScene(new Scene(root));

            newStage.initModality(Modality.APPLICATION_MODAL);

            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEditMedicine(MedicineDTO medicineDTO){

    }

    @FXML
    public void handleDeleteMedicine(MedicineDTO medicineDTO){}


    @FXML
    private void handleSearchById(KeyEvent event){
        try{
            if(event.getCode()== KeyCode.ENTER){
                String id = tIdField.getText();

                if(!id.matches(MEDICINE_ID_REGEX)){
                    new Alert(Alert.AlertType.ERROR , "Invalid Medicine ID!").show();


                }else {
                    MedicineDTO medicineDTO = medicineModel.searchMedicine(id);

                    if(medicineDTO != null){

                        ObservableList<MedicineDTO> obList =
                                FXCollections.observableArrayList(medicineDTO);
                        tableMedicine.setItems(obList);

                        clearFields();


                    }else{
                        new Alert(Alert.AlertType.ERROR , "Medicine ID not found!").show();
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

                if(!name.matches(MEDICINE_NAME_REGEX)){
                    new Alert(Alert.AlertType.ERROR , "Invalid Medicine Name!").show();


                }else {
                    MedicineDTO medicineDTO = medicineModel.searchMedicine(name);

                    if(medicineDTO != null){

                        ObservableList<MedicineDTO> obList =
                                FXCollections.observableArrayList(medicineDTO);
                        tableMedicine.setItems(obList);

                        clearFields();


                    }else{
                        new Alert(Alert.AlertType.ERROR , "Medicine not found!").show();
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



