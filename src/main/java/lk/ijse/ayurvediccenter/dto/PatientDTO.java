package lk.ijse.ayurvediccenter.dto;

public class PatientDTO {

    private int patientId;
    private String patientName;
    private String address;
    private String nic;
    private String contact;
    private String gender;
    private String dateOfBirth;

    public PatientDTO() {
    }

    public PatientDTO(int patientId, String patientName, String address, String nic, String contact) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.address = address;
        this.nic = nic;
        this.contact = contact;
    }

    public PatientDTO(String patientName, String address, String nic, String contact, String gender, String dateOfBirth) {
        this.patientName = patientName;
        this.address = address;
        this.nic = nic;
        this.contact = contact;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public PatientDTO(int patientId, String patientName, String address, String nic, String contact, String gender, String dateOfBirth) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.address = address;
        this.nic = nic;
        this.contact = contact;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", contact='" + contact + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
