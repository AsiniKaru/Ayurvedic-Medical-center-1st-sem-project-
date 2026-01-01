package lk.ijse.ayurvediccenter.dto.tm;

public class AppPrescriptionTM {
    private int appId;
    private int patientId;
    private int diagnosisId;
    private String diagnosisDetails;
    private String appDate;

    public AppPrescriptionTM() {
    }

    public AppPrescriptionTM(int appId, int diagnosisId, String diagnosisDetails, String appDate) {
        this.appId = appId;
        this.diagnosisId = diagnosisId;
        this.diagnosisDetails = diagnosisDetails;
        this.appDate = appDate;
    }

    public AppPrescriptionTM(int patientId, int diagnosisId, String diagnosisDetails, String appDate, int appId) {
        this.patientId = patientId;
        this.diagnosisId = diagnosisId;
        this.diagnosisDetails = diagnosisDetails;
        this.appDate = appDate;
        this.appId = appId;
    }

    public AppPrescriptionTM(int appId, int patientId, int diagnosisId, String diagnosisDetails, String appDate) {
        this.appId = appId;
        this.patientId = patientId;
        this.diagnosisId = diagnosisId;
        this.diagnosisDetails = diagnosisDetails;
        this.appDate = appDate;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getDiagnosisDetails() {
        return diagnosisDetails;
    }

    public void setDiagnosisDetails(String diagnosisDetails) {
        this.diagnosisDetails = diagnosisDetails;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    @Override
    public String toString() {
        return "AppPrescriptionTM{" +
                "appId=" + appId +
                ", patientId=" + patientId +
                ", diagnosisId=" + diagnosisId +
                ", diagnosisDetails='" + diagnosisDetails + '\'' +
                ", appDate='" + appDate + '\'' +
                '}';
    }
}

