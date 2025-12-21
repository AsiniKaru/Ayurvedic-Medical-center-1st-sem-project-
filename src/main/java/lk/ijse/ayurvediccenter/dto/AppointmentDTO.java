package lk.ijse.ayurvediccenter.dto;

public class AppointmentDTO {
    private int appointment_id ;
    private int doc_id ;
    private int patient_id ;
    private double doc_charges ;
    private double treatment_charges ;
    private double prescription_charges;
    private double total_charges ;
    private String appointment_date ;

    public AppointmentDTO() {
    }

    public AppointmentDTO(int doc_id, int patient_id, double doc_charges, double treatment_charges, double prescription_charges, double total_charges, String appointment_date) {
        this.doc_id = doc_id;
        this.patient_id = patient_id;
        this.doc_charges = doc_charges;
        this.treatment_charges = treatment_charges;
        this.prescription_charges = prescription_charges;
        this.total_charges = total_charges;
        this.appointment_date = appointment_date;
    }



    public AppointmentDTO(int appointment_id, int doc_id, int patient_id, double doc_charges, double treatment_charges, double prescription_charges, double total_charges, String appointment_date) {
        this.appointment_id = appointment_id;
        this.doc_id = doc_id;
        this.patient_id = patient_id;
        this.doc_charges = doc_charges;
        this.treatment_charges = treatment_charges;
        this.prescription_charges = prescription_charges;
        this.total_charges = total_charges;
        this.appointment_date = appointment_date;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public double getDoc_charges() {
        return doc_charges;
    }

    public void setDoc_charges(double doc_charges) {
        this.doc_charges = doc_charges;
    }

    public double getTreatment_charges() {
        return treatment_charges;
    }

    public void setTreatment_charges(double treatment_charges) {
        this.treatment_charges = treatment_charges;
    }

    public double getPrescription_charges() {
        return prescription_charges;
    }

    public void setPrescription_charges(double prescription_charges) {
        this.prescription_charges = prescription_charges;
    }

    public double getTotal_charges() {
        return total_charges;
    }

    public void setTotal_charges(double total_charges) {
        this.total_charges = total_charges;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "appointment_id=" + appointment_id +
                ", doc_id=" + doc_id +
                ", patient_id=" + patient_id +
                ", doc_charges=" + doc_charges +
                ", treatment_charges=" + treatment_charges +
                ", prescription_charges=" + prescription_charges +
                ", total_charges=" + total_charges +
                ", appointment_date='" + appointment_date + '\'' +
                '}';
    }
}
