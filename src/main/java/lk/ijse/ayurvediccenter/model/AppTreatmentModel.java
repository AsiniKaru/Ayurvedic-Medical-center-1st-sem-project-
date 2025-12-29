package lk.ijse.ayurvediccenter.model;

import lk.ijse.ayurvediccenter.dto.AppTreatmentDTO;
import lk.ijse.ayurvediccenter.dto.AppointmentDTO;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.util.List;

public class AppTreatmentModel {

    public boolean saveAppTreatment(int appointmentId, String appointmentDate, List<AppTreatmentDTO> treatmentList) throws Exception{

        for(AppTreatmentDTO appTreatmentDTO : treatmentList) {

            if (CrudUtil.execute(
                    "INSERT INTO App_Treatment (appointment_id,treatment_id,date,Treatment_name) VALUES (?,?,?,?)",
                    appointmentId,
                    appTreatmentDTO.getTreatmentId(),
                    appointmentDate,
                    appTreatmentDTO.getTreatmentName()

            )) {
                return true;

            } else {
                throw new Exception("Something Went wrong inserting data into Appointment_Treatment table");
            }

        }                return false;

    }
}
