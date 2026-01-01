package lk.ijse.ayurvediccenter.dto.tm;

public class AppMedicationTM {
    private int patientId;
    private int appId;
    private String appDate;
    private String medName;
    private int medQty;
    private String medInstruction;

    public AppMedicationTM() {
    }

    public AppMedicationTM(int patientId, int appId, String appDate, String medName, int medQty, String medInstruction) {
        this.patientId = patientId;
        this.appId = appId;
        this.appDate = appDate;
        this.medName = medName;
        this.medQty = medQty;
        this.medInstruction = medInstruction;
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

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getMedQty() {
        return medQty;
    }

    public void setMedQty(int medQty) {
        this.medQty = medQty;
    }

    public String getMedInstruction() {
        return medInstruction;
    }

    public void setMedInstruction(String medInstruction) {
        this.medInstruction = medInstruction;
    }

    @Override
    public String toString() {
        return "AppMedicationTM{" +
                "patientId=" + patientId +
                ", appId=" + appId +
                ", appDate='" + appDate + '\'' +
                ", medName='" + medName + '\'' +
                ", medQty='" + medQty + '\'' +
                ", medInstruction='" + medInstruction + '\'' +
                '}';
    }
}
