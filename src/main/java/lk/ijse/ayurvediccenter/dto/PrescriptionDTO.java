package lk.ijse.ayurvediccenter.dto;

public class PrescriptionDTO {
    private int prescription_id ;
    private String med_name ;
    private int dosage ;
    private int qty ;
    private double prescription_charges ;

    public PrescriptionDTO() {
    }

    public PrescriptionDTO(String med_name, int dosage, int qty, double prescription_charges) {
        this.med_name = med_name;
        this.dosage = dosage;
        this.qty = qty;
        this.prescription_charges = prescription_charges;
    }

    public PrescriptionDTO(int prescription_id, String med_name, int dosage, int qty, double prescription_charges) {
        this.prescription_id = prescription_id;
        this.med_name = med_name;
        this.dosage = dosage;
        this.qty = qty;
        this.prescription_charges = prescription_charges;
    }

    public int getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrescription_charges() {
        return prescription_charges;
    }

    public void setPrescription_charges(double prescription_charges) {
        this.prescription_charges = prescription_charges;
    }
}
