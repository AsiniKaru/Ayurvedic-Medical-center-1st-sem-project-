package lk.ijse.ayurvediccenter.dto.tm;

public class AppPatientTM {
    private int appointmentId;
    private int patientId;
    private String patientName;
    private String contact;
    private String appointmentDate;
    private String appointmentStatus;
    private String appointmentType;

    public AppPatientTM() {
    }

    public AppPatientTM(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public AppPatientTM(int patientId, String patientName, String contact, String appointmentDate, String appointmentStatus ,String appointmentType) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.contact = contact;
        this.appointmentDate = appointmentDate;
        this.appointmentStatus = appointmentStatus;
    }

    public AppPatientTM(int appointmentId, int patientId, String patientName, String contact, String appointmentDate, String appointmentStatus,String appointmentType) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.contact = contact;
        this.appointmentDate = appointmentDate;
        this.appointmentStatus = appointmentStatus;
        this.appointmentType = appointmentType;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentType() {
        return appointmentType;
    }
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    @Override
    public String toString() {
        return "AppointmentTableDTO{" +
                "appointmentId=" + appointmentId +
                ", patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", contact='" + contact + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", appointmentStatus='" + appointmentStatus + '\'' +
                ", appointmentType='" + appointmentType + '\'' +
                '}';
    }
}
