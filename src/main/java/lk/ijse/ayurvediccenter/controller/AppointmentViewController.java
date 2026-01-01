package lk.ijse.ayurvediccenter.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.*;
import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.model.MedicineModel;
import lk.ijse.ayurvediccenter.model.PatientModel;
import lk.ijse.ayurvediccenter.model.PrescriptionModel;
import lk.ijse.ayurvediccenter.model.TreatmentModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentViewController {

        @FXML private AnchorPane profileContent;

        @FXML private Button saveButton;

        @FXML private Text appDateField;

        @FXML private Text appIdField;

        @FXML private Text pNameField;

        @FXML private TextArea diagnosisField;

        @FXML private DatePicker dateField;

        @FXML private ComboBox<String> comboTreatmentType;

        @FXML private ComboBox<String> comboMedType;

        @FXML private TableColumn<PrescriptionMedDTO , Integer> colMedId;

        @FXML private TableColumn<PrescriptionMedDTO , String> colMedInstruction;

        @FXML private TableColumn<PrescriptionMedDTO , String> colMedName;

        @FXML private TableColumn<PrescriptionMedDTO , Integer> colMedQty;

        @FXML private TableColumn<TreatmentDTO , Integer> colTId;

        @FXML private TableColumn<TreatmentDTO , String> colTName;

        @FXML private TableColumn<TreatmentDTO, Void> colTAction;

        @FXML private TableColumn<PrescriptionMedDTO , Void> colMedAction;

        @FXML private TableView<PrescriptionMedDTO> tableMedicine;

        @FXML private TableView<TreatmentDTO> tableTreatment;

        @FXML private TextArea medInstructionField;

        @FXML private TextField medQtyField;

        @FXML private VBox medicineBox;

        @FXML private VBox treatmentBox;


        TreatmentModel treatmentModel = new TreatmentModel();
        MedicineModel medicineModel = new MedicineModel();
        PrescriptionModel prescriptionModel = new PrescriptionModel();
        PatientModel patientModel = new PatientModel();

        private ObservableList<PrescriptionMedDTO> prescriptionList = FXCollections.observableArrayList();
        private ObservableList<TreatmentDTO> treatmentList = FXCollections.observableArrayList();

        private AppointmentController appointmentController;
        public void setOnConsultationComplete(AppointmentController appointmentController) {
                this.appointmentController = appointmentController;
        }
        AppPatientTM currentAppPatientTM ;
        public int patientId;
        public void initData(AppPatientTM appPatientTM) {
                currentAppPatientTM = appPatientTM;
                patientId = appPatientTM.getPatientId();
                appIdField.setText(String.valueOf(appPatientTM.getAppointmentId()));
                appDateField.setText(String.valueOf(appPatientTM.getAppointmentDate()));
                pNameField.setText(appPatientTM.getPatientName());

                //////// this will customize the AppointmentView according to the appointment type/////////
                switch (appPatientTM.getAppointmentType()) {
                        case "Medication":
                                ///////// when appointment type = Medicine /////////
                                medicineBox.setVisible(true); medicineBox.setManaged(true);
                                treatmentBox.setVisible(false); treatmentBox.setManaged(false);
                                medicineBox.setPrefHeight(300); tableMedicine.setPrefHeight(250);
                                break;
                        case "Treatment":
                                ///////// when appointment type = Treatment /////////
                                treatmentBox.setVisible(true); treatmentBox.setManaged(true);
                                medicineBox.setVisible(false); medicineBox.setManaged(false);
                                treatmentBox.setPrefHeight(300);tableTreatment.setPrefHeight(250);
                                break;
                        default:
                                ////////// when appointment type = Med & Treatment /////////
                                medicineBox.setVisible(true); medicineBox.setManaged(true);
                                treatmentBox.setVisible(true); treatmentBox.setManaged(true);
                                break;
                }

                String today = LocalDate.now().toString();
                if(!appPatientTM.getAppointmentDate().equals(today)){

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error!");
                        alert.setHeaderText("Appointment can not be Edited , Future Appointment! ");

                        ButtonType buttonYes = new ButtonType("Okay", ButtonBar.ButtonData.CANCEL_CLOSE);
                        alert.getButtonTypes().setAll(buttonYes);
                        Optional<ButtonType> results = alert.showAndWait();


                        if (results.isPresent() && results.get() == buttonYes) {

                                Stage currentStage = (Stage) saveButton.getScene().getWindow();
                                currentStage.close();
                        }
                }

                loadTreatmentName();
                loadMedicineName();


                ///////////////////// Table Medicine ////////////////////////////
                colMedId.setCellValueFactory(new PropertyValueFactory<>("medId"));
                colMedName.setCellValueFactory(new PropertyValueFactory<>("medName"));
                colMedQty.setCellValueFactory(new PropertyValueFactory<>("medQty"));
                colMedInstruction.setCellValueFactory(new PropertyValueFactory<>("instruction"));
                colMedAction.setCellFactory(param -> new TableCell<>() {

                        private final Button btnRemove = new Button("Remove");
                        private final HBox hBox = new HBox(10, btnRemove);

                        {
                                // 🔹 Remove button action
                                btnRemove.setOnAction(event -> {
                                        PrescriptionMedDTO prescriptionMedDTO = getTableView()
                                                .getItems()
                                                .get(getIndex());

                                        onActionMedRemove(prescriptionMedDTO);
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
                //////////////////// Table Treatment ///////////////////////////
                colTId.setCellValueFactory(new PropertyValueFactory<>("treatment_id"));
                colTName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colTAction.setCellFactory(param -> new TableCell<>() {

                        private final Button btnRemove = new Button("Remove");
                        private final HBox hBox = new HBox(10, btnRemove);

                        {
                                // 🔹 Remove button action
                                btnRemove.setOnAction(event -> {
                                        TreatmentDTO treatmentDTO = getTableView()
                                                .getItems()
                                                .get(getIndex());

                                        onActionRemove(treatmentDTO);
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

        }


        @FXML
        public void loadTreatmentName(){
                try{
                        List<TreatmentDTO> treatmentList = treatmentModel.getTreatments();

                        ObservableList<String> obList = FXCollections.observableArrayList();
                        for(TreatmentDTO treatmentDTO : treatmentList){
                                obList.add(String.valueOf(treatmentDTO.getName()));
                        }
                        comboTreatmentType.setItems(obList);


                }catch(Exception e){
                        e.printStackTrace();
                }
        }

        @FXML
        public void loadMedicineName(){
                try{
                        List<MedicineDTO> medicineList = medicineModel.getMedicine();

                        ObservableList<String> obList = FXCollections.observableArrayList();
                        for(MedicineDTO medicineDTO : medicineList){
                                obList.add(String.valueOf(medicineDTO.getName()));
                        }
                        comboMedType.setItems(obList);


                }catch(Exception e){
                        e.printStackTrace();
                }
        }

        @FXML
        public void handleAddMedicine(ActionEvent event) {
                try {
                        System.out.println("Handling add medicine");
                        if ((comboMedType.getValue() == null)||(medQtyField.getText() == null)||(medInstructionField.getText() == null)) {
                                new Alert(Alert.AlertType.ERROR,"Medicine Field incomplete!",ButtonType.OK).show();
                                System.out.println("Handling add medicine 1 ");

                        } else{
                                System.out.println("Handling add medicine 2 ");


                                String medName = comboMedType.getSelectionModel().getSelectedItem();

                                MedicineDTO medicineDTO = medicineModel.searchTreatmentByName(medName);
                                        System.out.println(medicineDTO);

                                String medId = String.valueOf(medicineDTO.getMed_id());
                                String medQty = medQtyField.getText();
                                String medInstruction = medInstructionField.getText();

                                System.out.println(medName +","+ medId +","+ medQty +","+ medInstruction);

                                PrescriptionMedDTO prescriptionMedDTO = new PrescriptionMedDTO(
                                        Integer.parseInt(medId),
                                        medName,
                                        Integer.parseInt(medQty),
                                        medInstruction
                                );
                                System.out.println(prescriptionMedDTO);
                                prescriptionList.add(prescriptionMedDTO);
                                tableMedicine.setItems(prescriptionList);
                                addResetMedField();
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }
        @FXML
        public void handleAddTreatment(ActionEvent actionEvent) {

                if (comboTreatmentType.getValue() == null){
                        new Alert(Alert.AlertType.INFORMATION,"Treatment Type not filled!",ButtonType.OK).show();
                } else{
                        try {

                                TreatmentDTO treatmentDTO =
                                        treatmentModel.searchTreatmentByName(comboTreatmentType.getValue());

                                // prevent duplicates (optional but good)
                                if (!treatmentList.contains(treatmentDTO)) {
                                        treatmentList.add(treatmentDTO);
                                }else{
                                        new Alert(Alert.AlertType.INFORMATION, "treatment already added!", ButtonType.OK).show();
                                }

                                tableTreatment.setItems(treatmentList);
                                addRestTreatmentField();

                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }


        }

        @FXML
        public void handleSavePrescription(ActionEvent actionEvent) {
             try{

                     int appId = Integer.parseInt(appIdField.getText());
                     String pName = pNameField.getText();
                     String appDate = appDateField.getText();
                     String diagnosisDetails = diagnosisField.getText();
                     String nextVisitDate = dateField.getValue().toString();

                     List<PrescriptionMedDTO> prescriptionMeds = new ArrayList<>();
                     for (PrescriptionMedDTO prescriptionMedDTO : prescriptionList) {
                             PrescriptionMedDTO prescriptionMedList = new PrescriptionMedDTO(
                                     prescriptionMedDTO.getMedId(),
                                     prescriptionMedDTO.getMedName(),
                                     prescriptionMedDTO.getMedQty(),
                                     prescriptionMedDTO.getInstruction()
                             );
                             prescriptionMeds.add(prescriptionMedList);
                     }
                     PrescriptionDTO prescriptionDTO = new PrescriptionDTO(
                             appId,
                             diagnosisDetails,
                             nextVisitDate,
                             prescriptionMeds
                     );

                     boolean isConsulted = prescriptionModel.consultationDone(prescriptionDTO);
                     if (isConsulted) {
                             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                             alert.setTitle("Success!");
                             alert.setHeaderText("Consultation successfully completed! Would you like to go back to the Appointment Menu?");
                             ButtonType buttonYes = new ButtonType("Yes");
                             ButtonType buttonNo = new ButtonType("No");
                             alert.getButtonTypes().setAll(buttonYes, buttonNo);

                             Optional<ButtonType> result = alert.showAndWait();

                             if (result.isPresent() && result.get() == buttonYes) {
                                     Stage currentStage = (Stage) saveButton.getScene().getWindow();
                                     currentStage.close();


                             } else {

                             }

                     } else {
                             new Alert(Alert.AlertType.ERROR, "Failed to update the Appointment Status!", ButtonType.OK).show();
                     }



             }catch (Exception e){
                     e.printStackTrace();
                     new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();

             }

        }

        public void onActionMedRemove(PrescriptionMedDTO prescriptionMedDTO){
                tableMedicine.getItems().remove(prescriptionMedDTO);
        }

        public void onActionRemove(TreatmentDTO treatmentDTO) {

                tableTreatment.getItems().remove(treatmentDTO);
        }


        @FXML
        void onActionDiagnosisHistory(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/ayurvediccenter/view/DiagnosisView.fxml"));
                        AnchorPane anchorPane = loader.load();

                        DiagnosisViewController controller = loader.getController();
                        controller.setPatientId(patientId);
                        System.out.println(patientId);

                        profileContent.getChildren().clear();
                        anchorPane.prefWidthProperty().bind(profileContent.widthProperty());
                        anchorPane.prefHeightProperty().bind(profileContent.heightProperty());
                        profileContent.getChildren().add(anchorPane);

                } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
                        e.printStackTrace();
                }


        }

        @FXML
        void onActionMedicationHistory(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/ayurvediccenter/view/MedicationView.fxml"));
                        AnchorPane anchorPane = loader.load();

                        MedicationViewController controller = loader.getController();

                        int appId = Integer.parseInt(appIdField.getText());
                        controller.initData(appId);

                        profileContent.getChildren().clear();
                        anchorPane.prefWidthProperty().bind(profileContent.widthProperty());
                        anchorPane.prefHeightProperty().bind(profileContent.heightProperty());
                        profileContent.getChildren().add(anchorPane);

                } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
                        e.printStackTrace();
                }

        }

        @FXML
        public void onActionAppointmentHistory(ActionEvent actionEvent) {
                try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/ayurvediccenter/view/AppointmentHistory.fxml"));
                        AnchorPane anchorPane = loader.load();

                        AppointmentHistoryController controller = loader.getController();

                        controller.initData(patientId);

                        profileContent.getChildren().clear();
                        anchorPane.prefWidthProperty().bind(profileContent.widthProperty());
                        anchorPane.prefHeightProperty().bind(profileContent.heightProperty());
                        profileContent.getChildren().add(anchorPane);

                } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
                        e.printStackTrace();

                }
        }

        @FXML
        void onActionOverview(ActionEvent event) {
                try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/ayurvediccenter/view/PatientProfile.fxml"));
                        AnchorPane anchorPane = loader.load();

                        PatientProfileController controller = loader.getController();

                        PatientDTO patientDTO = patientModel.searchPatient(String.valueOf(patientId));
                        controller.initData(patientDTO);
                        controller.setUpdate(false);

                        profileContent.getChildren().clear();
                        anchorPane.prefWidthProperty().bind(profileContent.widthProperty());
                        anchorPane.prefHeightProperty().bind(profileContent.heightProperty());
                        profileContent.getChildren().add(anchorPane);

                } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
                        e.printStackTrace();

                }

        }

        @FXML
        void onActionAppointmentOverview(ActionEvent event) {

                try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/ayurvediccenter/view/AppointmentOverview.fxml"));
                        AnchorPane anchorPane = loader.load();

                        AppointmentOverviewController controller = loader.getController();

                        controller.initData(currentAppPatientTM);

                        profileContent.getChildren().clear();
                        anchorPane.prefWidthProperty().bind(profileContent.widthProperty());
                        anchorPane.prefHeightProperty().bind(profileContent.heightProperty());
                        profileContent.getChildren().add(anchorPane);

                } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
                        e.printStackTrace();

                }

        }


        private void addResetMedField(){
                comboMedType.setValue(null);
                medQtyField.setText("");
                medInstructionField.setText("");
        }

        private void addRestTreatmentField(){
                comboTreatmentType.setValue(null);

        }

        @FXML
        public void onActionReset(){
                diagnosisField.setText("");
                comboMedType.setValue(null);
                medQtyField.setText("");
                medInstructionField.setText("");
                tableMedicine.getItems().clear();
                comboTreatmentType.setValue(null);
                tableTreatment.getItems().clear();
                dateField.setValue(null);
        }


        @FXML
        public void navigateTo(String path) {
                try {
                        profileContent.getChildren().clear();
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
                        anchorPane.prefWidthProperty().bind(profileContent.widthProperty());
                        anchorPane.prefHeightProperty().bind(profileContent.heightProperty());
                        profileContent.getChildren().add(anchorPane);
                } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
                        e.printStackTrace();
                }
        }


}