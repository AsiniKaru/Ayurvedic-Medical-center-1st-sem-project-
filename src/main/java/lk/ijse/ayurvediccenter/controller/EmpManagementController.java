package lk.ijse.ayurvediccenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import lk.ijse.ayurvediccenter.dto.EmployeeDTO;
import lk.ijse.ayurvediccenter.model.EmployeeModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmpManagementController implements Initializable {

    @FXML private TableColumn<EmployeeDTO,String> colAddress;

    @FXML private TableColumn<EmployeeDTO,String> colContactnum;

    @FXML private TableColumn<EmployeeDTO,String> colEmail;

    @FXML private TableColumn<EmployeeDTO,String> colFName;

    @FXML private TableColumn<EmployeeDTO,Integer> colId;

    @FXML private TableColumn<EmployeeDTO,String> colLName;

    @FXML private TableColumn<EmployeeDTO,Void> colAction;

    @FXML private TextField idField;

    @FXML private TextField nameField;

    @FXML private TableView<EmployeeDTO> tableEmployees;

    EmployeeModel employeeModel = new EmployeeModel();
    EmpManagementController controllerRef = this;

    private final String EMPLOYEE_ID_REGEX = "^[0-9]+$";
    private final String EMPLOYEE_NAME_REGEX = "^[a-zA-Z]{3,}$";

    public void initialize(URL url, ResourceBundle rb) {

            System.out.println("Emp Management FXML is loaded!");

            colId.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
            colFName.setCellValueFactory(new PropertyValueFactory<>("fName"));
            colLName.setCellValueFactory(new PropertyValueFactory<>("lName"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colContactnum.setCellValueFactory(new PropertyValueFactory<>("contact_num"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            tableEmployees.setFixedCellSize(70);
            tableEmployees.setStyle("-fx-font-size: 12;");

                colAction.setCellFactory(param -> new TableCell<>() {

                    private final Button btnEdit = new Button("Edit");
                    private final Button btnDelete = new Button("Delete");
                    private final HBox hBox = new HBox(10, btnEdit, btnDelete);

                    {
                        // 🔹 Edit button action
                        btnEdit.setOnAction(event -> {
                            EmployeeDTO employeeDTO = getTableView().getItems().get(getIndex());

                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("/lk/ijse/ayurvediccenter/view/EditProfile.fxml")
                            );

                            try {
                                Parent root = loader.load();

                                EditProfileController controller = loader.getController();
                                controller.setInitData(employeeDTO,controllerRef);

                                Stage stage = new Stage();
                                stage.setTitle("Edit Employee Profile");
                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });

                        // 🔹 Delete button action
                        btnDelete.setOnAction(event -> {
                            EmployeeDTO employeeDTO = getTableView()
                                    .getItems()
                                    .get(getIndex());

                            handleDeleteEmployee(employeeDTO);
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


            loadEmployeeTable();

//        tableEmployee.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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
    public void loadEmployeeTable(){
        clearFields();
        try{
            List<EmployeeDTO> employeeList = employeeModel.getEmployees();

            ObservableList<EmployeeDTO> obList = FXCollections.observableArrayList();

            for(EmployeeDTO employeeDTO : employeeList){
                obList.add(employeeDTO);
            }
            tableEmployees.setItems(obList);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDeleteEmployee(EmployeeDTO employeeDTO){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete?",
                ButtonType.YES, ButtonType.NO);

        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            tableEmployees.getItems().remove(employeeDTO);

            try{
                boolean isDeleted = employeeModel.deleteEmployee(String.valueOf(employeeDTO.getEmp_id()));

                if(isDeleted){
                    new Alert(Alert.AlertType.INFORMATION, "Employee deleted successfully!").show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();

                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleAddEmployee(ActionEvent actionEvent)  {

        try{

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/lk/ijse/ayurvediccenter/view/Profile.fxml"));

            Parent root = loader.load();

            ProfileController profileController = loader.getController();
            profileController.setEmpManagementController(this);


            Stage newStage = new Stage();
            newStage.setTitle("Add New Employee");
            newStage.setScene(new Scene(root));

            newStage.initModality(Modality.APPLICATION_MODAL);

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

                if(!id.matches(EMPLOYEE_ID_REGEX)){
                    new Alert(Alert.AlertType.ERROR , "Invalid Employee ID!").show();


                }else {
                    EmployeeDTO employeeDTO =employeeModel.searchEmployee(Integer.parseInt(id));

                    if(employeeDTO != null){

                        ObservableList<EmployeeDTO> obList =
                                FXCollections.observableArrayList(employeeDTO);
                        tableEmployees.setItems(obList);

                        clearFields();


                    }else{
                        new Alert(Alert.AlertType.ERROR , "Employee ID not found!").show();
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

                if(!name.matches(EMPLOYEE_NAME_REGEX)){
                    new Alert(Alert.AlertType.ERROR , "Invalid Employee Name!").show();


                }else {
                    EmployeeDTO employeeDTO = employeeModel.searchEmployeeByName(name);

                    if(employeeDTO != null){

                        ObservableList<EmployeeDTO> obList =
                                FXCollections.observableArrayList(employeeDTO);
                        tableEmployees.setItems(obList);

                        clearFields();


                    }else{
                        new Alert(Alert.AlertType.ERROR , "Employee not found!").show();
                        clearFields();

                    }

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void clearFields(){
        idField.clear();
        nameField.clear();
    }
}
