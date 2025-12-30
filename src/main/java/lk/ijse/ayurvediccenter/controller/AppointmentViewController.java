//package lk.ijse.ayurvediccenter.controller;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.layout.VBox;
//import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
//
//public class AppointmentViewController {
//
//
//        @FXML private VBox diagnosisContent;
//        @FXML private VBox medicineContent;
//        @FXML private VBox treatmentContent;
//
//        public void initData(AppPatientTM selectedPatient){
//
//        }
//
//        public void initialize() {
//            // Initialize sections as hidden
//            hideSection(diagnosisContent);
//            hideSection(medicineContent);
//            hideSection(treatmentContent);
//        }
//
//        private void hideSection(VBox section) {
//            section.setVisible(false);
//            section.setManaged(false);
//        }
//
//        private void toggleSection(VBox section) {
//            boolean isVisible = section.isVisible();
//            section.setVisible(!isVisible);
//            section.setManaged(!isVisible);
//        }
//
//        @FXML
//        void toggleDiagnosisSection() {
//            toggleSection(diagnosisContent);
//        }
//
//        @FXML
//        void toggleMedicineSection() {
//            toggleSection(medicineContent);
//        }
//
//        @FXML
//        void toggleTreatmentSection() {
//            toggleSection(treatmentContent);
//        }
//
//        @FXML
//        void btnSaveOnAction() {
//            // Your logic to save to Prescription, Prescription_Med, and App_Treatment tables
//        }
//
//    @FXML
//    void btnResetOnAction(ActionEvent event) {
//        txtDiagnosis.clear();
//        txtMedInstruction.clear();
//        txtTreatmentInstruction.clear();
//    }
//
//    private void toggle(VBox vbox) {
//        boolean visible = vbox.isVisible();
//        vbox.setVisible(!visible);
//        vbox.setManaged(!visible);
//    }
//
//    private void hide(VBox vbox) {
//        vbox.setVisible(false);
//        vbox.setManaged(false);
//    }
//}
//
//
