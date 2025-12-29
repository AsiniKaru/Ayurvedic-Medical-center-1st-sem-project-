package lk.ijse.ayurvediccenter.dto;

public class AppTreatmentDTO {
    int appTreatmentId;
    int appointmentId;
    int treatmentId;
    String treatmentName;
    String date;

    public AppTreatmentDTO() {
    }

    public AppTreatmentDTO(int appTreatmentId) {
        this.appTreatmentId = appTreatmentId;
    }


    public AppTreatmentDTO(int treatmentId, String treatmentName ) {
        this.treatmentId = treatmentId;
        this.treatmentName = treatmentName;
    }

    public AppTreatmentDTO(int appointmentId, int treatmentId, String treatmentName, String date) {
        this.appointmentId = appointmentId;
        this.treatmentId = treatmentId;
        this.treatmentName = treatmentName;
        this.date = date;
    }

    public AppTreatmentDTO(int appTreatmentId, int appointmentId, int treatmentId, String treatmentName, String date) {
        this.appTreatmentId = appTreatmentId;
        this.appointmentId = appointmentId;
        this.treatmentId = treatmentId;
        this.treatmentName = treatmentName;
        this.date = date;
    }

    public int getAppTreatmentId() {
        return appTreatmentId;
    }

    public void setAppTreatmentId(int appTreatmentId) {
        this.appTreatmentId = appTreatmentId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AppTreatmentDTO{" +
                "appTreatmentId=" + appTreatmentId +
                ", appointmentId=" + appointmentId +
                ", treatmentId=" + treatmentId +
                ", treatmentName='" + treatmentName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
