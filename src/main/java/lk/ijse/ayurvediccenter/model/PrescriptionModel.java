package lk.ijse.ayurvediccenter.model;

import lk.ijse.ayurvediccenter.db.DBConnection;
import lk.ijse.ayurvediccenter.dto.PrescriptionDTO;
import lk.ijse.ayurvediccenter.dto.PrescriptionMedDTO;
import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.model.enums.AppointmentStatus;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionModel {

    private AppointmentModel appointmentModel = new AppointmentModel();
    private MedicineModel medicineModel = new MedicineModel();

    public boolean consultationDone(PrescriptionDTO prescriptionDTO) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();

        try{
            conn.setAutoCommit(false);
            boolean isConsultationDone = CrudUtil.execute(
                    "INSERT INTO Prescription (prescription_id , appointment_id , diagnosis_details , next_visit_date) VALUES (?,?,?,?);",
                    prescriptionDTO.getPrescriptionId(),
                    prescriptionDTO.getAppointmentId(),
                    prescriptionDTO.getDiagnosisDetails(),
                    prescriptionDTO.getNextVisitDate()
            );
            if(isConsultationDone){
                    ResultSet rs = CrudUtil.execute("SELECT prescription_id FROM Prescription ORDER BY prescription_id DESC LIMIT 1");
                    if(rs.next()) {
                        int PrescriptionId = rs.getInt("prescription_id");
                        List<PrescriptionMedDTO> prescriptionMeds = prescriptionDTO.getPrescriptionMeds();
                        updatePrescriptionMed(PrescriptionId, prescriptionMeds);
                    }
            if(isConsultationDone) {
                appointmentModel.changeAppStatusToConsulted(String.valueOf(prescriptionDTO.getAppointmentId()));

            }else{
                    throw new Exception("Something went wrong when Updating Appointment status.");

                }
            }else{
                throw new Exception("Something went wrong when Updating Prescription Medicine ");

            }
            conn.commit();
            return true;

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }

    }

    public List<PrescriptionMedDTO> getPrescriptionMedList(int patientId) throws Exception {
        ResultSet rs =
                CrudUtil.execute(
                       " SELECT pm.med_id AS medId, m.med_name AS medName, pm.medicine_qty  AS medQty ,"+
                               "pm.med_instruction AS instruction "+
                        "FROM Patient p JOIN Appointment a ON p.patient_id = a.patient_id" +
                        "JOIN Prescription pr ON a.appointment_id = pr.appointment_id JOIN Prescription_Med pm"+
                        "ON pr.prescription_id = pm.prescription_id JOIN Medicine m ON pm.med_id = m.med_id"+
                       " WHERE p.patient_id = ?",
                        patientId

                );
        List<PrescriptionMedDTO> prescriptionMedList = new ArrayList<>();

        while (rs.next()) {
            PrescriptionMedDTO prescriptionMedDTO = new PrescriptionMedDTO(
                    rs.getInt("medId"),
                    rs.getString("medName"),
                    rs.getInt("medQty"),
                    rs.getString("instruction")
            );

            prescriptionMedList.add(prescriptionMedDTO);
        }

        return prescriptionMedList;

    }

//    public List<PrescriptionDTO> getPrescription(int patientId) throws Exception {
//
//    }

    public boolean updatePrescriptionMed(int id , List<PrescriptionMedDTO>  medList) throws Exception {
        for (PrescriptionMedDTO prescriptionMedDTO : medList) {
            if( CrudUtil.execute(
                    "INSERT INTO Prescription_Med(prescription_id, med_id ,medicine_name,medicine_qty,med_instruction) VALUES (?,?,?,?,?)",
                    id,
                    prescriptionMedDTO.getMedId(),
                    prescriptionMedDTO.getMedName(),
                    prescriptionMedDTO.getMedQty(),
                    prescriptionMedDTO.getInstruction()
            )){
                if(!medicineModel.decreaseMedQty(prescriptionMedDTO.getMedId() , prescriptionMedDTO.getMedQty())){

                    throw new Exception("SomethingWent Wrong decreasing the med qty");
                }

            }else{

                throw new Exception("SomethingWent Whent wrong inserting data into order table");

            }
        }
        return true;

    }

}
