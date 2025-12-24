package lk.ijse.ayurvediccenter.dto;

public class MedicineDTO {
    private int med_id;
    private String name;
    private String description;
    private int qty;
    private double price;


    private boolean editing;

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public MedicineDTO() {
    }

    public MedicineDTO(String name, String description, int qty, double price) {
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.price = price;
    }

    public MedicineDTO(int med_id, String name, String description, int qty, double price) {
        this.med_id = med_id;
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.price = price;
    }

    public int getMed_id() {
        return med_id;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MedicineDTO{" +
                "med_id=" + med_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
