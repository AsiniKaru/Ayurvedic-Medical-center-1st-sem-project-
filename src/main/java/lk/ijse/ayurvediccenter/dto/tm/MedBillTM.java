package lk.ijse.ayurvediccenter.dto.tm;

public class MedBillTM {
    private int med_id;
    private String med_name;
    private Double unit_price;
    private int medicine_qty;

    public MedBillTM() {
    }

    public MedBillTM(int med_id, String med_name, Double unit_price, int medicine_qty) {
        this.med_id = med_id;
        this.med_name = med_name;
        this.unit_price = unit_price;
        this.medicine_qty = medicine_qty;
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

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public int getMedicine_qty() {
        return medicine_qty;
    }

    public void setMedicine_qty(int medicine_qty) {
        this.medicine_qty = medicine_qty;
    }

    @Override
    public String toString() {
        return "MedBillTM{" +
                "med_id=" + med_id +
                ", med_name='" + med_name + '\'' +
                ", unit_price=" + unit_price +
                ", medicine_qty=" + medicine_qty +
                '}';
    }
}
