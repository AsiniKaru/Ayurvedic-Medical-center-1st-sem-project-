package lk.ijse.ayurvediccenter.dto;

public class DoctorDTO {
    private int doctor_id;
    private String doc_name ;
    private String contact_num ;
    private String email ;
    private double doc_charges;

    public DoctorDTO() {
    }

    public DoctorDTO(String doc_name, String contact_num, String email, double doc_charges) {
        this.doc_name = doc_name;
        this.contact_num = contact_num;
        this.email = email;
        this.doc_charges = doc_charges;
    }

    public DoctorDTO(int doctor_id, String doc_name, String contact_num, String email, double doc_charges) {
        this.doctor_id = doctor_id;
        this.doc_name = doc_name;
        this.contact_num = contact_num;
        this.email = email;
        this.doc_charges = doc_charges;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
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

    public double getDoc_charges() {
        return doc_charges;
    }

    public void setDoc_charges(double doc_charges) {
        this.doc_charges = doc_charges;
    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "doctor_id=" + doctor_id +
                ", doc_name='" + doc_name + '\'' +
                ", contact_num='" + contact_num + '\'' +
                ", email='" + email + '\'' +
                ", doc_charges=" + doc_charges +
                '}';
    }
}
