package lk.ijse.ayurvediccenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import lk.ijse.ayurvediccenter.dto.DoctorDTO;
import lk.ijse.ayurvediccenter.dto.PatientDTO;
import lk.ijse.ayurvediccenter.dto.TreatmentDTO;
import lk.ijse.ayurvediccenter.model.AppointmentModel;
import lk.ijse.ayurvediccenter.model.DoctorModel;
import lk.ijse.ayurvediccenter.model.PatientModel;
import lk.ijse.ayurvediccenter.model.TreatmentModel;
import lk.ijse.ayurvediccenter.controller.PatientController;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @FXML private TableView<?> tableAppTreatment;

    @FXML private Text appIDField;

    @FXML private Label lblTreatmentType;

    private PatientModel patientModel =  new PatientModel();
    private AppointmentModel appointmentModel = new AppointmentModel();
    private TreatmentModel treatmentModel = new TreatmentModel();
    private DoctorModel doctorModel = new DoctorModel();

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
    public void handleAddTreatment(ActionEvent event) {

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
}
