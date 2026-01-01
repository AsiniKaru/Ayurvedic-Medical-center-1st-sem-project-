package lk.ijse.ayurvediccenter.model;

import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.dto.tm.AppPrescriptionTM;
import lk.ijse.ayurvediccenter.dto.tm.PatientAppointmentTM;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppPrescriptionModel {

    //    this Method will give details for diagnosis History table
    public List<AppPrescriptionTM> getDiagnosisHistory(int patientId) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT a.appointment_id, a.appointment_date, " +
                        "    p.prescription_id, p.diagnosis_details, " +
                        "    a.patient_id FROM Appointment a LEFT JOIN  Prescription p ON a.appointment_id = p.appointment_id " +
                        "WHERE a.patient_id = ? ",
                patientId
        );
        List<AppPrescriptionTM> diagnosisList = new ArrayList<>();

        while (rs.next()) {
            AppPrescriptionTM appPrescriptionTM = new AppPrescriptionTM(
                    rs.getInt("appointment_id"),
                    rs.getInt("patient_id"),
                    rs.getInt("prescription_id"),
                    rs.getString("diagnosis_details"),
                    rs.getString("appointment_date")
            );
            diagnosisList.add(appPrescriptionTM);
        }
        return diagnosisList;
    }


    //    this Method will give details for diagnosis History table
    public List<PatientAppointmentTM> getAppHistory(int patientId) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT    a.patient_id AS patient_id, "+
                        "a.appointment_id AS appointment_id," +
                        " a.appointment_id AS AppId, a.appointment_date AS AppDate," +
                        "a.app_type AS AppType,a.app_statues AS AppStatus," +
                        "a.doc_charges AS DocCharges," +
                        " a.prescription_charges AS PrescriptionCharges," +
                        " a.treatment_charges AS TreatmentCharges," +
                        " a.total_charges AS TotalCharges, p.next_visit_date AS NextVisitDate " +
                        "FROM Appointment a LEFT JOIN Prescription p ON a.appointment_id = p.appointment_id " +
                        "WHERE a.patient_id = ? ",
                patientId
        );
        List<PatientAppointmentTM> appointmentList = new ArrayList<>();

        while (rs.next()) {
            PatientAppointmentTM patientAppointmentTM = new PatientAppointmentTM(
                    rs.getInt("patient_id"),
                    rs.getInt("appointment_id"),
                    rs.getString("AppDate"),
                    rs.getString("AppType"),
                    rs.getString("AppStatus"),
                    rs.getDouble("DocCharges"),
                    rs.getDouble("PrescriptionCharges"),
                    rs.getDouble("TreatmentCharges"),
                    rs.getDouble("TotalCharges"),
                    rs.getString("NextVisitDate")
            );
            appointmentList.add(patientAppointmentTM);
        }
        return appointmentList;
    }
}