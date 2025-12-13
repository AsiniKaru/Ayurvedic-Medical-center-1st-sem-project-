package lk.ijse.ayurvediccenter.dto;

public class TreatmentDTO {
    private int treatment_id;
    private String treatment_type;
    private double treatment_charges;
    private String description;

    public TreatmentDTO() {
    }

    public TreatmentDTO(String treatment_type, double treatment_charges, String description) {
        this.treatment_type = treatment_type;
        this.treatment_charges = treatment_charges;
        this.description = description;
    }

    public TreatmentDTO(int treatment_id, String treatment_type, double treatment_charges, String description) {
        this.treatment_id = treatment_id;
        this.treatment_type = treatment_type;
        this.treatment_charges = treatment_charges;
        this.description = description;
    }

    public int getTreatment_id() {
        return treatment_id;
    }

    public void setTreatment_id(int treatment_id) {
        this.treatment_id = treatment_id;
    }

    public String getTreatment_type() {
        return treatment_type;
    }

    public void setTreatment_type(String treatment_type) {
        this.treatment_type = treatment_type;
    }

    public double getTreatment_charges() {
        return treatment_charges;
    }

    public void setTreatment_charges(double treatment_charges) {
        this.treatment_charges = treatment_charges;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TreatmentDTO{" +
                "treatment_id=" + treatment_id +
                ", treatment_type='" + treatment_type + '\'' +
                ", treatment_charges=" + treatment_charges +
                ", description='" + description + '\'' +
                '}';
    }
}
