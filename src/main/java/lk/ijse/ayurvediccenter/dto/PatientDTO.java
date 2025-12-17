package lk.ijse.ayurvediccenter.dto;

import java.time.LocalDate;
import java.util.Date;

public class PatientDTO {

    private int patientId;
    private String firstName;
    private String lastName;
    private String address;
    private String nic;
    private String contact;
    private String gender;
    private Date dateOfBirth;
    private Date patientSince;

    public PatientDTO() {
    }

    public PatientDTO(int patientId, String firstName, String lastName, String address, String contact) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contact = contact;
    }

    public PatientDTO(String firstName, String lastName, String address, String nic, String contact, String gender, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.nic = nic;
        this.contact = contact;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public PatientDTO(String firstName, String lastName, String address, String nic, String contact, String gender, Date dateOfBirth, Date patientSince) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.nic = nic;
        this.contact = contact;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.patientSince = patientSince;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getPatientSince() {
        return patientSince;
    }

    public void setPatientSince(LocalDate patientSince) {
        this.patientSince = patientSince;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", contact='" + contact + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", patientSince=" + patientSince +
                '}';
    }
}
