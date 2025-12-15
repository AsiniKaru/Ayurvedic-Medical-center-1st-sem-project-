package lk.ijse.ayurvediccenter.dto;

public class PatientDTO {

    private int patient_id;
    private String patient_name;
    private String address;
    private String patient_nic;
    private String contact_num;
    private String gender;
    private String date_of_birth;

    public PatientDTO() {
    }

    public PatientDTO(String patient_name, String address, String patient_nic, String contact_num, String gender, String date_of_birth) {
        this.patient_name = patient_name;
        this.address = address;
        this.patient_nic = patient_nic;
        this.contact_num = contact_num;
        this.gender = gender;
        this.date_of_birth = date_of_birth;

    }

    public PatientDTO(int patient_id, String patient_name, String address, String patient_nic, String contact_num) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.address = address;
        this.patient_nic = patient_nic;
        this.contact_num = contact_num;
    }

    public PatientDTO(int patient_id, String patient_name, String address, String patient_nic, String contact_num, String gender, String date_of_birth) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.address = address;
        this.patient_nic = patient_nic;
        this.contact_num = contact_num;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPatient_nic() {
        return patient_nic;
    }

    public void setPatient_nic(String patient_nic) {
        this.patient_nic = patient_nic;
    }

    public String getContact_num() {
        return contact_num;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "patient_id=" + patient_id +
                ", patient_name='" + patient_name + '\'' +
                ", address='" + address + '\'' +
                ", patient_nic='" + patient_nic + '\'' +
                ", contact_num='" + contact_num + '\'' +
                ", gender='" + gender + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                '}';
    }
}

