package lk.ijse.ayurvediccenter.model;

import lk.ijse.ayurvediccenter.dto.tm.AppMedicationTM;
import lk.ijse.ayurvediccenter.dto.tm.AppPrescriptionTM;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppMedicationModel {

//   this Method will give Medication history table data
    public List<AppMedicationTM> getMedHistory(int patientId) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT  a.patient_id AS patientId,a.appointment_id, a.appointment_date," +
                        " pm.medicine_name, pm.medicine_qty, pm.med_instruction " +
                        "FROM  Appointment a " +
                        "JOIN Prescription p ON a.appointment_id = p.appointment_id " +
                        "JOIN Prescription_Med pm ON p.prescription_id = pm.prescription_id " +
                        "WHERE a.patient_id = ?",
                patientId
        );
        List<AppMedicationTM> medicationList = new ArrayList<>();
        while (rs.next()) {
            AppMedicationTM appMedicationTM = new AppMedicationTM(
                    rs.getInt("patientId"),
                    rs.getInt("appointment_id"),
                    rs.getString("appointment_date"),
                    rs.getString("medicine_name"),
                    rs.getInt("medicine_qty"),
                    rs.getString("med_instruction")
            );
            medicationList.add(appMedicationTM);
        }
        return medicationList;
    }
}