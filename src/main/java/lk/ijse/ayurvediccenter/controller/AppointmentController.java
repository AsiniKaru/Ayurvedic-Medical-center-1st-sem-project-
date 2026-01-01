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
import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.model.AppointmentModel;
import lk.ijse.ayurvediccenter.model.enums.UserRole;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentController  implements Initializable {

    @FXML private TableColumn<AppPatientTM, Void> colAction;

    @FXML private TableColumn<AppPatientTM, String> colAppDate;

    @FXML private TableColumn<AppPatientTM, Integer> colAppId;

    @FXML private TableColumn<AppPatientTM, String> colAppStatus;

    @FXML private TableColumn<AppPatientTM,String> colContactNum;

    @FXML private TableColumn<AppPatientTM,String> colName;

    @FXML private TableColumn<AppPatientTM, Integer>  colPId;

    @FXML private TableColumn<AppPatientTM, Integer>  colAppType;

    @FXML private TableView<AppPatientTM> tableAppointment;

    @FXML private DatePicker dateField;

    @FXML private TextField idField;

    @FXML private Button addAppButton;

    AppointmentModel appointmentModel = new AppointmentModel();

    private final String PATIENT_ID_REGEX = "^[0-9]+$";
    private final String PATIENT_FIRST_NAME_REGEX = "^[a-zA-Z]{3,}$";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Appointment FXML is loaded!");

        colAppId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colPId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colAppDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        colName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colContactNum.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAppType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        colAppStatus.setCellValueFactory(new PropertyValueFactory<>("appointmentStatus"));
        colAction.setCellFactory(param -> new TableCell<>() {

            private final Button btnEdit = new Button("Edit");
            private final Button btnDelete = new Button("Delete");
            private final HBox hBox = new HBox(10, btnEdit, btnDelete);

            {
                // 🔹 Edit button action
                btnEdit.setOnAction(event -> {
                    AppPatientTM appPatientTM = getTableView()
                            .getItems()
                            .get(getIndex());

                    handleEditAppointment(appPatientTM);
                });

                // 🔹 Delete button action
                btnDelete.setOnAction(event -> {
                    AppPatientTM appPatientTM = getTableView()
                            .getItems()
                            .get(getIndex());

                    handleDeleteAppointment(appPatientTM);
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



        loadTodayAppointmentTable();


        tableAppointment.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    openAppointmentView(newSelection);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void openAppointmentView(AppPatientTM selectedPatient) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/ayurvediccenter/view/AppointmentView.fxml"));
        Parent parent = loader.load();

        AppointmentViewController appointmentViewController = loader.getController();
        appointmentViewController.initData(selectedPatient);
        appointmentViewController.setOnConsultationComplete(this);


        Stage stage = new Stage();
        stage.setTitle("Appointment :  " + selectedPatient.getPatientName());
        stage.setScene(new Scene(parent));

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }

    @FXML
    private void onActionAddAppointment() {
        try{

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/lk/ijse/ayurvediccenter/view/AddAppointment.fxml"));

            Parent root = loader.load();

            AddAppointmentController addController = loader.getController();
            addController.setAppointmentController(this);
            addController.setUpdate(false);


            Stage newStage = new Stage();
            newStage.setTitle("Add New Appointment");
            newStage.setScene(new Scene(root));

            newStage.initModality(Modality.APPLICATION_MODAL);

            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void loadTodayAppointmentTable(){
        try{
            List<AppPatientTM> appointmentList = appointmentModel.getTodayAppointments();

            ObservableList<AppPatientTM> obList = FXCollections.observableArrayList();

            for(AppPatientTM appointmentDTO : appointmentList){
                obList.add(appointmentDTO);
            }
            tableAppointment.setItems(obList);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEditAppointment(AppPatientTM appPatientTM) {
        try{

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/lk/ijse/ayurvediccenter/view/AddAppointment.fxml"));

            Parent root = loader.load();

            AddAppointmentController addController = loader.getController();
            addController.setAppointmentController(this);
            addController.setUpdate(true);
            addController.setAppDetails(appPatientTM);



            Stage newStage = new Stage();
            newStage.setTitle("Edit Appointment");
            newStage.setScene(new Scene(root));

            newStage.initModality(Modality.APPLICATION_MODAL);

            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void  handleDeleteAppointment(AppPatientTM appPatientTM){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete?",
                ButtonType.YES, ButtonType.NO);

        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            tableAppointment.getItems().remove(appPatientTM);

            try{
                boolean isDeleted = appointmentModel.deleteAppointment(String.valueOf(appPatientTM.getAppointmentId()));

                if(isDeleted){
                    new Alert(Alert.AlertType.INFORMATION, "Appointment deleted successfully!").show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();

                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onActionSearchByDate(){

        try{
            String appDate = dateField.getValue().toString();

            List<AppPatientTM> appointmentList = appointmentModel.getAppointmentsByDate(appDate);
            ObservableList<AppPatientTM> obList = FXCollections.observableArrayList();
            for(AppPatientTM appointmentDTO : appointmentList){
                obList.add(appointmentDTO);

            }
            tableAppointment.setItems(obList);

        }catch(Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    public void onActionSearchByPatientId(KeyEvent event){

        try {
            if(event.getCode()== KeyCode.ENTER) {
                String pId = idField.getText();

                if(!pId.matches(PATIENT_ID_REGEX)){
                    new Alert(Alert.AlertType.ERROR , "Invalid Patient ID!").show();
                }else {
                    List<AppPatientTM> appointmentList = appointmentModel.getAppointmentById(pId);
                    ObservableList<AppPatientTM> obList = FXCollections.observableArrayList();
                    for(AppPatientTM appointmentDTO : appointmentList) {
                        obList.add(appointmentDTO);
                    }
                    tableAppointment.setItems(obList);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onActionReset(){
        dateField.setValue(null);
        idField.setText("");

        loadTodayAppointmentTable();
    }


}