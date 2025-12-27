package lk.ijse.ayurvediccenter.dto;

public class DoctorDTO {
    private int doctor_id;
    private String fname ;
    private String lname;
    private String address;
    private String contact_num ;
    private String email ;
    private double dcharges;

    public DoctorDTO() {
    }

    public DoctorDTO(int doctor_id) {
        this.doctor_id = doctor_id;
    }



    public DoctorDTO(String fname, String lname, String address, String contact_num, String email, double dcharges) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.contact_num = contact_num;
        this.email = email;
        this.dcharges = dcharges;
    }

    public DoctorDTO(int doctor_id, String fname, String lname, String address, String contact_num, String email, double dcharges) {
        this.doctor_id = doctor_id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.contact_num = contact_num;
        this.email = email;
        this.dcharges = dcharges;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_num() {
        return contact_num;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getDcharges() {
        return dcharges;
    }

    public void setDcharges(double dcharges) {
        this.dcharges = dcharges;
    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "doctor_id=" + doctor_id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", address='" + address + '\'' +
                ", contact_num='" + contact_num + '\'' +
                ", email='" + email + '\'' +
                ", dcharges=" + dcharges +
                '}';
    }
}
