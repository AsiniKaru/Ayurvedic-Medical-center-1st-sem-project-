package lk.ijse.ayurvediccenter.model;

import lk.ijse.ayurvediccenter.dto.AppTreatmentDTO;
import lk.ijse.ayurvediccenter.dto.AppointmentDTO;
import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppTreatmentModel {

    public boolean saveAppTreatment(int appointmentId, String appointmentDate, List<AppTreatmentDTO> treatmentList) throws Exception{

        for (AppTreatmentDTO appTreatmentDTO : treatmentList) {

            boolean isInserted = CrudUtil.execute(
                    "INSERT INTO App_Treatment (appointment_id, treatment_id, date, Treatment_name) VALUES (?,?,?,?)",
                    appointmentId,
                    appTreatmentDTO.getTreatmentId(),
                    appointmentDate,
                    appTreatmentDTO.getTreatmentName()
            );

            if (!isInserted) {
                throw new Exception("Something went wrong inserting data into App_Treatment table");
            }
        }

        return true;
    }

//   this Method will give appTreatment details of a specific appointment_id
    public List<AppTreatmentDTO> getTreatmentList(AppPatientTM appId) throws Exception{
        List<AppTreatmentDTO> treatmentList = new ArrayList<>();

        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM App_Treatment WHERE appointment_id =?",
                appId.getAppointmentId()
        );
        while (rs.next()) {
            AppTreatmentDTO appTreatmentDTO = new AppTreatmentDTO(
                rs.getInt("app_treatment_id"),
                    rs.getInt("appointment_id"),
                    rs.getInt("treatment_id"),
                    rs.getString("date"),
                    rs.getString("Treatment_name")
            );
            treatmentList.add(appTreatmentDTO);
        }
        return treatmentList;
    }

    public boolean deleteTreatment(int appTreatmentId) throws Exception {
        return CrudUtil.execute(
                "DELETE FROM App_Treatment WHERE app_treatment_id = ?",
                appTreatmentId
        );
    }

    public boolean updateTreatment(AppTreatmentDTO treatment) throws Exception {
        return CrudUtil.execute(
                "UPDATE App_Treatment SET treatment_id=?, date=?, Treatment_name=? WHERE app_treatment_id=?",
                treatment.getTreatmentId(),
                treatment.getDate(),
                treatment.getTreatmentName(),
                treatment.getAppTreatmentId()
        );
    }

}

