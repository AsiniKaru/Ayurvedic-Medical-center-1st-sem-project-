package lk.ijse.ayurvediccenter.dto;

public class MedicineDTO {
    private int med_id;
    private String med_name;
    private String description;
    private int stack_qty;
    private double unit_price;

    public MedicineDTO() {
    }

    public MedicineDTO(String med_name, String description, int stack_qty, double unit_price) {
        this.med_name = med_name;
        this.description = description;
        this.stack_qty = stack_qty;
        this.unit_price = unit_price;
    }

    public MedicineDTO(int med_id, String med_name, String description, int stack_qty, double unit_price) {
        this.med_id = med_id;
        this.med_name = med_name;
        this.description = description;
        this.stack_qty = stack_qty;
        this.unit_price = unit_price;
    }

    public int getMed_id() {
        return med_id;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStack_qty() {
        return stack_qty;
    }

    public void setStack_qty(int stack_qty) {
        this.stack_qty = stack_qty;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "MedicineDTO{" +
                "med_id=" + med_id +
                ", med_name='" + med_name + '\'' +
                ", description='" + description + '\'' +
                ", stack_qty=" + stack_qty +
                ", unit_price=" + unit_price +
                '}';
    }
}
