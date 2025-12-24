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
import javafx.stage.StageStyle;
import lk.ijse.ayurvediccenter.dto.MedicineDTO;
import lk.ijse.ayurvediccenter.dto.TreatmentDTO;
import lk.ijse.ayurvediccenter.model.MedicineModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicineController implements Initializable {

    @FXML
    private TableColumn<MedicineDTO,Void> colAction;

    @FXML
    private TableColumn<MedicineDTO,String>  colDesc;

    @FXML
    private TableColumn<MedicineDTO,Integer>  colId;

    @FXML
    private TableColumn<MedicineDTO,String>  colName;

    @FXML
    private TableColumn<MedicineDTO,Double>  colPrice;

    @FXML
    private TableColumn<MedicineDTO,Integer>  colQty;


    @FXML
    private TextField tIdField;

    @FXML
    private TextField tNameField;

    @FXML
    private TableView<MedicineDTO>  tableMedicine;

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


        colAction.setCellFactory(param -> new TableCell<>() {

            private final Button btnEdit = new Button("Edit");
            private final Button btnDelete = new Button("Delete");
            private final HBox hBox = new HBox(10, btnEdit, btnDelete);

            {
                // 🔹 Edit button action
                btnEdit.setOnAction(event -> {
                    MedicineDTO medicine = getTableView()
                            .getItems()
                            .get(getIndex());

                    handleEditMedicine(medicine);
                });

                // 🔹 Delete button action
                btnDelete.setOnAction(event -> {
                    MedicineDTO medicine = getTableView()
                            .getItems()
                            .get(getIndex());

                    handleDeleteMedicine(medicine);
                });

                hBox.setStyle("-fx-alignment: CENTER;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hBox);
                }
            }
        });

        // Optional: fixed row height for other rows
        tableMedicine.setFixedCellSize(30);
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
            addController.setUpdate(false);



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

        if (medicineDTO == null) {
            new Alert(Alert.AlertType.WARNING, "No medicine selected!").show();
            return;
        }

        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("/lk/ijse/ayurvediccenter/view/AddMedicine.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            Logger.getLogger(AddMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }

        AddMedicineController addMedicineController = loader.getController();
        addMedicineController.setMedicineController(this);
        addMedicineController.setUpdate(true);
        addMedicineController.setTextField(
                medicineDTO.getMed_id(),
                medicineDTO.getName(),
                medicineDTO.getDescription(),
                medicineDTO.getQty(),
                medicineDTO.getPrice()
        );
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();


    }

    @FXML
    public void handleDeleteMedicine(MedicineDTO medicineDTO) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete?",
                ButtonType.YES, ButtonType.NO);

        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            tableMedicine.getItems().remove(medicineDTO);

            try{
                boolean isDeleted = medicineModel.deleteMedicine(String.valueOf(medicineDTO.getMed_id()));

                if(isDeleted){
                    new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully!").show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();

                }

            }catch(Exception e){
                    e.printStackTrace();
            }
        }
    }


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



