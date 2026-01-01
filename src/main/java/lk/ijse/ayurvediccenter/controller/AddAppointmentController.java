
package lk.ijse.ayurvediccenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.*;
import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.model.*;
import lk.ijse.ayurvediccenter.model.enums.AppointmentStatus;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class AddAppointmentController implements Initializable {

    @FXML private ComboBox<String> comboAppointmentType;

    @FXML private ComboBox<String> comboDocId;

    @FXML private TextField docNameField;

    @FXML private ComboBox<String> comboPatientId;

    @FXML private ComboBox<String> comboTreatmentType;

    @FXML private TextField contactField;

    @FXML private DatePicker dateField;

    @FXML private TextField pNameField;

    @FXML private Button saveButton;

    @FXML private Button restButton;

    @FXML private Button addTreatmentButton;

    @FXML private TableColumn<?, ?> colTId;

    @FXML private TableColumn<?, ?> colTName;

    @FXML private TableColumn<TreatmentDTO, Void> colActionRemove;

    @FXML private TableView<TreatmentDTO> tableAppTreatment;

    @FXML private Text appIDField;

    @FXML private Label lblTreatmentType;

    @FXML private Button resetButton;

    private PatientModel patientModel =  new PatientModel();
    private AppointmentModel appointmentModel = new AppointmentModel();
    private TreatmentModel treatmentModel = new TreatmentModel();
    private DoctorModel doctorModel = new DoctorModel();
    private AppTreatmentModel  appTreatmentModel = new AppTreatmentModel();

    private ObservableList<TreatmentDTO> treatmentList = FXCollections.observableArrayList();

    private AppointmentController appointmentController;
    private PatientController patientController;

   private boolean update;
   private boolean isPatientAdd = false;

   public void setAppController(PatientController patientController) {
       this.patientController = patientController;
       resetButton.setDisable(true);
       resetButton.setVisible(false);
   }

    public void setAppointmentController(AppointmentController appointmentController) {
        this.appointmentController = appointmentController;
        resetButton.setVisible(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colTId.setCellValueFactory(new PropertyValueFactory<>("treatment_id"));
        colTName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colActionRemove.setCellFactory(param -> new TableCell<>() {

            private final Button btnRemove = new Button("Remove");
            private final HBox hBox = new HBox(10, btnRemove);

            {
                // 🔹 Remove button action
                btnRemove.setOnAction(event -> {
                    TreatmentDTO treatmentDTO = getTableView()
                            .getItems()
                            .get(getIndex());

                    handleRemoveTreatment(treatmentDTO);
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


        comboAppointmentType.getItems().addAll(
                "Medication",
                "Treatment",
                "Med & Treatment"
        );

        // 👇 LISTENER (IMPORTANT)
        comboAppointmentType.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    handleAppointmentType(newVal);
                });

        loadPatientId();
        loadDocId();
        loadAppointmentId();
        invisible();

    }

    @FXML
    public void handleSelectDoctorId(ActionEvent event) {
        try {
            String selectedDoctorId = comboDocId.getSelectionModel().getSelectedItem();
            String doctorName = doctorModel.getDoctorName(selectedDoctorId);

            docNameField.setText(doctorName );

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    public void handleSelectPatientId(ActionEvent event) {
        try{
            String selectedPatientId = comboPatientId.getSelectionModel().getSelectedItem();
            PatientDTO patientDTO = patientModel.searchPatient(selectedPatientId);

            pNameField.setText(patientDTO.getFirstName()+" "+patientDTO.getLastName());
            contactField.setText(patientDTO.getContact());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void handleSaveAppointment( ) {
        try{
                String doctorId = comboDocId.getSelectionModel().getSelectedItem();
                String patientId = comboPatientId.getSelectionModel().getSelectedItem();
                String appDate = dateField.getValue().toString();
                double docCharges = doctorModel.getDoctorCharges(Integer.parseInt(doctorId));
                AppointmentStatus appStatus = AppointmentStatus.ACTIVE;
                String appType = comboAppointmentType.getSelectionModel().getSelectedItem();


                List<AppTreatmentDTO> appTreatmentList = new ArrayList<>();
                if(!appType.equals("Medication")) {
                    for (TreatmentDTO treatmentType : treatmentList) {
                        appTreatmentList.add(
                                new AppTreatmentDTO(
                                        treatmentType.getTreatment_id(),
                                        treatmentType.getName()
                                )
                        );
                    }
                }

                AppointmentDTO appointmentDTO = new AppointmentDTO(
                        Integer.parseInt(doctorId),
                        Integer.parseInt(patientId),
                        docCharges,
                        appDate,
                        appType,
                        appStatus,
                        appTreatmentList
                );

            if(!update){
                if(appointmentModel.isAppointmentExists(patientId ,appDate)) {
                    new Alert(Alert.AlertType.WARNING, "Appointment already exists!").show();
                } else {
                    ////////////////// ADD Appointment ///////////////////////////////
                    boolean isAppointmentPlaced = appointmentModel.saveAppointment(appointmentDTO);

                    if (isAppointmentPlaced) {
                        if (!isPatientAdd) {
                            appointmentController.loadTodayAppointmentTable();
                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText("Appointment successfully Placed");

                        ButtonType buttonYes = new ButtonType("Okay");
                        alert.getButtonTypes().setAll(buttonYes);

                        Optional<ButtonType> results = alert.showAndWait();


                        if (results.isPresent() && results.get() == buttonYes) {
                            Stage currentStage = (Stage) saveButton.getScene().getWindow();
                            currentStage.close();
                            onActionReset();

                        }
                    }
                }
            }else{
                ////////////////// UPDATE Appointment ///////////////////////////////
                int appId = Integer.parseInt(appIDField.getText());
                System.out.println(appId);
                boolean isAppointmentUpdated = appointmentModel.updateAppointment( appId,appointmentDTO);

                if (isAppointmentUpdated) {

                    if(!isPatientAdd){
                        appointmentController.loadTodayAppointmentTable();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("Appointment successfully Updated. Would you like to make another change?");

                    ButtonType buttonYes = new ButtonType("Yes");
                    ButtonType buttonNo = new ButtonType("No");
                    alert.getButtonTypes().setAll(buttonYes, buttonNo);

                    Optional<ButtonType> results = alert.showAndWait();


                    if (results.isPresent() && results.get() == buttonYes) {



                    } else {

                        Stage currentStage = (Stage) saveButton.getScene().getWindow();
                        currentStage.close();
                        onActionReset();
                        if(!isPatientAdd){
                            appointmentController.loadTodayAppointmentTable();
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
        }
    }

    @FXML
    public void handleAddTreatment(ActionEvent event) {

        if (comboTreatmentType.getValue() == null) return;

        try {
            TreatmentDTO treatmentDTO =
                    treatmentModel.searchTreatmentByName(comboTreatmentType.getValue());

            // prevent duplicates (optional but good)
            if (!treatmentList.contains(treatmentDTO)) {
                treatmentList.add(treatmentDTO);
            }else{
                new Alert(Alert.AlertType.INFORMATION, "treatment already added!", ButtonType.OK).show();
            }

            tableAppTreatment.setItems(treatmentList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPatientId(){
        try{
            List<PatientDTO> patientList = patientModel.getPatients();

            ObservableList<String> obList = FXCollections.observableArrayList();

            for(PatientDTO patientDTO : patientList){
                obList.add(String.valueOf(patientDTO.getPatientId()));
            }

            comboPatientId.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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
    public void handleRemoveTreatment(TreatmentDTO treatmentDTO){

        tableAppTreatment.getItems().remove(treatmentDTO);

    }

    public void loadDocId(){
        try {
            List<DoctorDTO> doctorList = doctorModel.getDoctors();

            ObservableList<String> obList = FXCollections.observableArrayList();
            for(DoctorDTO doctorDTO : doctorList){
                obList.add(String.valueOf(doctorDTO.getDoctor_id()));
            }
            comboDocId.setItems(obList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getDate(ActionEvent event) {
        LocalDate appDate = dateField.getValue();
        String dateFormat = appDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println(dateFormat);
    }

    private void handleAppointmentType(String selectedType){
        if (selectedType == null) return;

        switch (selectedType) {

            case "Medication":
                System.out.println("Medication selected");
                invisible();
                break;

            case "Treatment":
                System.out.println("Treatment selected");
                loadTreatmentName();
                visible();
                break;

            case "Med & Treatment":
                System.out.println("Medication & Treatment selected");
                loadTreatmentName();
                visible();
                break;
        }

    }

    public void loadAppointmentId(){
        try {
            appIDField.setText(String.valueOf(appointmentModel.getNextAppId()));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void invisible(){

        lblTreatmentType.setVisible(false);
        addTreatmentButton.setVisible(false);
        comboTreatmentType.setVisible(false);
        tableAppTreatment.setVisible(false);
        dateField.setEditable(false);

    }

    private void visible(){
        lblTreatmentType.setVisible(true);
        addTreatmentButton.setVisible(true);
        comboTreatmentType.setVisible(true);
        tableAppTreatment.setVisible(true);
    }

    @FXML
    public void onActionReset(){

            comboPatientId.setValue(null);
            pNameField.clear();
            contactField.clear();

            comboAppointmentType.setValue(null);
            comboDocId.setValue(null);
            docNameField.clear();
            dateField.setValue(null);

            comboTreatmentType.setValue(null);
            treatmentList.clear();
            tableAppTreatment.refresh();
            invisible();
    }

    public void setUpdate(boolean b) {
        this.update = b;

    }

    public void setPatientUpdate(boolean isPatientAdd){
       this.isPatientAdd =isPatientAdd;

    }

    public void setAppDetails(AppPatientTM appPatientTM) {
        try{

            appIDField.setText(String.valueOf(appPatientTM.getAppointmentId()));
            String appId = appIDField.getText();

            comboPatientId.setValue(String.valueOf(appPatientTM.getPatientId()));
            pNameField.setText(String.valueOf(appPatientTM.getPatientName()));
            contactField.setText(String.valueOf(appPatientTM.getContact()));

            comboAppointmentType.setValue(String.valueOf(appPatientTM.getAppointmentType()));

            int docId =doctorModel.getDocId(appId);
            comboDocId.setValue(String.valueOf(docId));
            docNameField.setText(doctorModel.getDoctorName(String.valueOf(docId)));

            dateField.setValue(LocalDate.parse(appPatientTM.getAppointmentDate()));

            treatmentList.clear();

            if(!appPatientTM.getAppointmentType().equals("Medication")){
                visible();
                List<AppTreatmentDTO> tList =appTreatmentModel.getSelectedTreatmentList(appId);

                for(AppTreatmentDTO appTreatmentDTO : tList){
                    treatmentList.add(new TreatmentDTO(
                                    appTreatmentDTO.getTreatmentId(),
                                    appTreatmentDTO.getTreatmentName()
                    ));

                }

                tableAppTreatment.setItems(treatmentList);

            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void setPatientDetails(PatientDTO  patientDTO) {
        comboPatientId.setValue(String.valueOf(patientDTO.getPatientId()));
        pNameField.setText(patientDTO.getFirstName()+" "+patientDTO.getLastName());
        contactField.setText(patientDTO.getContact());
    }
}
