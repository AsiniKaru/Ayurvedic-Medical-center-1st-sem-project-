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
    private String dateOfBirth;
    private String patientSince;

    public PatientDTO() {
    }

    public PatientDTO(String firstName, String patientSince, String dateOfBirth, String gender, String contact, String nic, String address, String lastName) {
        this.firstName = firstName;
        this.patientSince = patientSince;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contact = contact;
        this.nic = nic;
        this.address = address;
        this.lastName = lastName;
    }

    public PatientDTO(int patientId, String firstName, String lastName, String address, String contact) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contact = contact;
    }

    public PatientDTO(String firstName,String LastName, String address, String nic, String contact, String gender, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = LastName;
        this.address = address;
        this.nic = nic;
        this.contact = contact;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public PatientDTO(int patientId, String firstName, String lastName, String address, String nic, String contact, String gender, String dateOfBirth) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.nic = nic;
        this.contact = contact;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public PatientDTO(int patientId, String firstName, String lastName, String address, String nic, String contact, String gender, String dateOfBirth, String patientSince) {
        this.patientId = patientId;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPatientSince() {
        return patientSince;
    }

    public void setPatientSince(String patientSince) {
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
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", patientSince='" + patientSince + '\'' +
                '}';
    }
}
