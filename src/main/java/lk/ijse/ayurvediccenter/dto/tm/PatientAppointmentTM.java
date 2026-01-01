package lk.ijse.ayurvediccenter.dto.tm;

public class PatientAppointmentTM {
    private int patientId;
    private int appId;
    private String Date ;
    private String appType;
    private String appStatus;
    private Double docCharges;
    private double prescriptionCharge;
    private double treatmentCharge;
    private double totalCharge;
    private String nextVisitDate;

    public PatientAppointmentTM() {
    }

    public PatientAppointmentTM(int appId, String date, String appType, String appStatus,double docCharges, double prescriptionCharge, double treatmentCharge, double totalCharge, String nextVisitDate) {
        this.appId = appId;
        this.Date = date;
        this.appType = appType;
        this.appStatus = appStatus;
        this.docCharges = docCharges;
        this.prescriptionCharge = prescriptionCharge;
        this.treatmentCharge = treatmentCharge;
        this.totalCharge = totalCharge;
        this.nextVisitDate = nextVisitDate;
    }

    public PatientAppointmentTM(int patientId, int appId, String date, String appType, String appStatus, Double docCharges, double prescriptionCharge, double treatmentCharge, double totalCharge, String nextVisitDate) {
        this.patientId = patientId;
        this.appId = appId;
        Date = date;
        this.appType = appType;
        this.appStatus = appStatus;
        this.docCharges = docCharges;
        this.prescriptionCharge = prescriptionCharge;
        this.treatmentCharge = treatmentCharge;
        this.totalCharge = totalCharge;
        this.nextVisitDate = nextVisitDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public double getPrescriptionCharge() {
        return prescriptionCharge;
    }

    public void setPrescriptionCharge(double prescriptionCharge) {
        this.prescriptionCharge = prescriptionCharge;
    }

    public double getTreatmentCharge() {
        return treatmentCharge;
    }

    public void setTreatmentCharge(double treatmentCharge) {
        this.treatmentCharge = treatmentCharge;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public String getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(String nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }

    public Double getDocCharges() {
        return docCharges;
    }

    public void setDocCharges(Double docCharges) {
        this.docCharges = docCharges;
    }

    @Override
    public String toString() {
        return "PatientAppointmentTM{" +
                "patientId=" + patientId +
                ", appId=" + appId +
                ", Date='" + Date + '\'' +
                ", appType='" + appType + '\'' +
                ", appStatus='" + appStatus + '\'' +
                ", docCharges=" + docCharges +
                ", prescriptionCharge=" + prescriptionCharge +
                ", treatmentCharge=" + treatmentCharge +
                ", totalCharge=" + totalCharge +
                ", nextVisitDate='" + nextVisitDate + '\'' +
                '}';
    }
}
