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
import lk.ijse.ayurvediccenter.dto.*;
import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.model.AppointmentModel;
import lk.ijse.ayurvediccenter.model.DoctorModel;
import lk.ijse.ayurvediccenter.model.PatientModel;
import lk.ijse.ayurvediccenter.model.TreatmentModel;
import lk.ijse.ayurvediccenter.model.enums.AppointmentStatus;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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

    @FXML private TableColumn<?, ?> colTPrice;

    @FXML private TableColumn<TreatmentDTO, Void> colActionRemove;

    @FXML private TableView<TreatmentDTO> tableAppTreatment;

    @FXML private Text appIDField;

    @FXML private Label lblTreatmentType;

    private PatientModel patientModel =  new PatientModel();
    private AppointmentModel appointmentModel = new AppointmentModel();
    private TreatmentModel treatmentModel = new TreatmentModel();
    private DoctorModel doctorModel = new DoctorModel();

    private ObservableList<TreatmentDTO> treatmentList = FXCollections.observableArrayList();

    private AppointmentController addController;
    private PatientController patientController;

    public void setPatientController(PatientController patientController) {
        this.patientController =patientController;
    }
    public void setAppointmentController(AppointmentController addController) {
        this.addController = addController;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colTId.setCellValueFactory(new PropertyValueFactory<>("treatment_id"));
        colTName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
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
                "Medication & Treatment"
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
            DoctorDTO doctorDTO = doctorModel.searchDoctor(Integer.parseInt(selectedDoctorId));

            docNameField.setText(doctorDTO.getFname()+" "+doctorDTO.getLname() );

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
    void handleSaveAppointment(ActionEvent event) {
        try{
            String doctorId = comboDocId.getSelectionModel().getSelectedItem();
            String patientId = comboPatientId.getSelectionModel().getSelectedItem();
            String appDate = dateField.getValue().toString();
            double docCharges = doctorModel.getDoctorCharges(Integer.parseInt(doctorId));
            AppointmentStatus appStatus = AppointmentStatus.ACTIVE;
            String appType = comboAppointmentType.getSelectionModel().getSelectedItem();


            List<AppTreatmentDTO> appTreatmentList = new ArrayList<>();

            for(TreatmentDTO treatmentType : treatmentList ){

                    AppTreatmentDTO appTreatmentDTO = new AppTreatmentDTO(
                            treatmentType.getTreatment_id(),
                            treatmentType.getName()
                    );
                    appTreatmentList.add(appTreatmentDTO);

                    AppointmentDTO appointmentDTO = new AppointmentDTO(
                        Integer.parseInt(doctorId),
                        Integer.parseInt(patientId),
                        docCharges,
                        appDate,
                        appType,
                        appStatus,
                        appTreatmentList
            );

            boolean isAppointmentPlaced = appointmentModel.saveAppointment(appointmentDTO);

            if (isAppointmentPlaced) {
                new Alert(Alert.AlertType.INFORMATION, "Appointment Successfully Saved", ButtonType.OK).show();
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

            case "Medication & Treatment":
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
    private void onActionReset(){
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
}
