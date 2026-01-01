package lk.ijse.ayurvediccenter.dto;

public class TreatmentDTO {
    private int treatment_id;
    private String name;
    private String type;
    private String description;
    private double price;

    public TreatmentDTO() {
    }

    public TreatmentDTO(int treatment_id) {
        this.treatment_id = treatment_id;
    }

    public TreatmentDTO(int treatment_id, String name) {
        this.treatment_id = treatment_id;
        this.name = name;
    }

    public TreatmentDTO(String name, String type , String description, double price) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
    }

    public TreatmentDTO(int treatment_id, String name, String type, String description, double price) {
        this.treatment_id = treatment_id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public int getTreatment_id() {
        return treatment_id;
    }

    public void setTreatment_id(int treatment_id) {
        this.treatment_id = treatment_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TreatmentDTO{" +
                "treatment_id=" + treatment_id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
