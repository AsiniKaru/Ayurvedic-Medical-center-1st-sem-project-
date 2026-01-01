package lk.ijse.ayurvediccenter.dto;

import java.util.List;

public class PrescriptionDTO {
    private int prescriptionId;
    private int appointmentId;
    private String diagnosisDetails;
    private String nextVisitDate;
    List<PrescriptionMedDTO> prescriptionMeds;


    public PrescriptionDTO() {
    }

    public PrescriptionDTO(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public PrescriptionDTO(int appointmentId, String diagnosisDetails, String nextVisitDate) {
        this.appointmentId = appointmentId;
        this.diagnosisDetails = diagnosisDetails;
        this.nextVisitDate = nextVisitDate;
    }

    public PrescriptionDTO(int prescriptionId, int appointmentId, String diagnosisDetails, String nextVisitDate) {
        this.prescriptionId = prescriptionId;
        this.appointmentId = appointmentId;
        this.diagnosisDetails = diagnosisDetails;
        this.nextVisitDate = nextVisitDate;
    }

    public PrescriptionDTO(int prescriptionId, int appointmentId, String diagnosisDetails, String nextVisitDate, List<PrescriptionMedDTO> prescriptionMeds) {
        this.prescriptionId = prescriptionId;
        this.appointmentId = appointmentId;
        this.diagnosisDetails = diagnosisDetails;
        this.nextVisitDate = nextVisitDate;
        this.prescriptionMeds = prescriptionMeds;
    }


    public PrescriptionDTO(int appointmentId, String diagnosisDetails, String nextVisitDate, List<PrescriptionMedDTO> prescriptionMeds) {
        this.appointmentId = appointmentId;
        this.diagnosisDetails = diagnosisDetails;
        this.nextVisitDate = nextVisitDate;
        this.prescriptionMeds = prescriptionMeds;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDiagnosisDetails() {
        return diagnosisDetails;
    }

    public void setDiagnosisDetails(String diagnosisDetails) {
        this.diagnosisDetails = diagnosisDetails;
    }

    public String getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(String nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }

    public List<PrescriptionMedDTO> getPrescriptionMeds() {
        return prescriptionMeds;
    }

    public void setPrescriptionMeds(List<PrescriptionMedDTO> prescriptionMeds) {
        this.prescriptionMeds = prescriptionMeds;
    }

    @Override
    public String toString() {
        return "PrescriptionDTO{" +
                "prescriptionId=" + prescriptionId +
                ", appointmentId='" + appointmentId + '\'' +
                ", diagnosisDetails='" + diagnosisDetails + '\'' +
                ", nextVisitDate='" + nextVisitDate + '\'' +
                ", prescriptionMeds=" + prescriptionMeds +
                '}';
    }
}