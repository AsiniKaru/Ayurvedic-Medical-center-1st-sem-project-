package lk.ijse.ayurvediccenter.model;

import javafx.fxml.FXML;
import lk.ijse.ayurvediccenter.db.DBConnection;
import lk.ijse.ayurvediccenter.dto.AppointmentDTO;
import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.model.enums.AppointmentStatus;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel {

    private AppTreatmentModel appTreatmentModel = new AppTreatmentModel();
    String today = LocalDate.now().toString();

//  this Method will get the details of all the (Today)Appointment of the table
    public List<AppPatientTM> getTodayAppointments() throws SQLException {

        ResultSet rs =
                CrudUtil.execute(
                        "SELECT a.appointment_id, p.patient_id, " +
                                "CONCAT(p.patient_fName, ' ', p.patient_lName) AS patient_name, " +
                                "p.contact_num, a.appointment_date, a.app_statues, a.app_type " +
                                "FROM Appointment a " +
                                "JOIN Patient p ON a.patient_id = p.patient_id " +
                                "WHERE a.appointment_date = ? AND a.app_statues = ?",
                        today,
                        AppointmentStatus.ACTIVE.name()
                );
        List<AppPatientTM> appointmentlist = new ArrayList<>();

        while (rs.next()) {
            AppPatientTM appPatientTM = new AppPatientTM(
                    rs.getInt("appointment_id"),
                    rs.getInt("patient_id"),
                    rs.getString("patient_name"),
                    rs.getString("contact_num"),
                    rs.getString("appointment_date"),
                    rs.getString("app_statues"),
                    rs.getString("app_type")

            );
            appointmentlist.add(appPatientTM);
        }

        return appointmentlist;

    }

//  this Method will get the details of all the Appointment of a specific day
    public List<AppPatientTM> getAppointmentsByDate(String date) throws SQLException {

        ResultSet rs =
                CrudUtil.execute(
                        "SELECT a.appointment_id, p.patient_id, " +
                                "CONCAT(p.patient_fName, ' ', p.patient_lName) AS patient_name, " +
                                "p.contact_num, a.appointment_date, a.app_statues, a.app_type " +
                                "FROM Appointment a " +
                                "JOIN Patient p ON a.patient_id = p.patient_id " +
                                "WHERE a.appointment_date = ?",
                        date
                );
        List<AppPatientTM> appointmentlist = new ArrayList<>();

        while (rs.next()) {
            AppPatientTM appPatientTM = new AppPatientTM(
                    rs.getInt("appointment_id"),
                    rs.getInt("patient_id"),
                    rs.getString("patient_name"),
                    rs.getString("contact_num"),
                    rs.getString("appointment_date"),
                    rs.getString("app_statues"),
                    rs.getString("app_type")

            );

            System.out.println(appPatientTM);
            appointmentlist.add(appPatientTM);
        }

        return appointmentlist;

    }

//   this Method will get appointment details by Patient id
    public List<AppPatientTM> getAppointmentById (String pId)throws  SQLException {

        ResultSet rs =
                CrudUtil.execute(
                        "SELECT a.appointment_id, p.patient_id, " +
                                "CONCAT(p.patient_fName, ' ', p.patient_lName) AS patient_name, " +
                                "p.contact_num, a.appointment_date, a.app_statues, a.app_type " +
                                "FROM Appointment a " +
                                "JOIN Patient p ON a.patient_id = p.patient_id " +
                                "WHERE p.patient_id = ?",
                        pId
                );
        List<AppPatientTM> appointmentlist = new ArrayList<>();

        while (rs.next()) {
            AppPatientTM appPatientTM = new AppPatientTM(
                    rs.getInt("appointment_id"),
                    rs.getInt("patient_id"),
                    rs.getString("patient_name"),
                    rs.getString("contact_num"),
                    rs.getString("appointment_date"),
                    rs.getString("app_statues"),
                    rs.getString("app_type")

            );

            System.out.println(appPatientTM);
            appointmentlist.add(appPatientTM);
        }

        return appointmentlist;


    }

//  this Method will Save a new Appointment to the Appointment Table
    public boolean saveAppointment(AppointmentDTO appointmentDTO) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();

        try{
            conn.setAutoCommit(false);
            boolean isAppointmentPlaced  = CrudUtil.execute(
                    "INSERT INTO Appointment (doc_id , patient_id ,doc_charges , appointment_date ,app_type , app_statues ) VALUES (?,?,?,?,?,?)",
                    appointmentDTO.getDoc_id(),
                    appointmentDTO.getPatient_id(),
                    appointmentDTO.getDoc_charges(),
                    appointmentDTO.getAppointment_date(),
                    appointmentDTO.getAppType(),
                    appointmentDTO.getAppStatus().name()

            );
            if (isAppointmentPlaced) {
                ResultSet rs = CrudUtil.execute("SELECT appointment_id FROM Appointment ORDER BY appointment_id DESC LIMIT 1 ");
                if (rs.next()) {
                    int appointmentId = rs.getInt("appointment_id");

                    if (!appointmentDTO.getAppType().equals("Medication")
                            && appointmentDTO.getTreatmentList() != null
                            && !appointmentDTO.getTreatmentList().isEmpty()) {
                        appTreatmentModel.saveAppTreatment(appointmentId, appointmentDTO.getAppointment_date(), appointmentDTO.getTreatmentList());
                    }
                }else{
                    throw new Exception("Something went wrong when finding the Appointment id");
                }
            }else{
                throw new Exception("Something went wrong when place the Appointment ");

            }
            conn.commit();
            return true;

        }catch(Exception e){
            conn.rollback();
            throw e;
        } finally {
        conn.setAutoCommit(true);
         }

    }

//  this Method will update the Appointment table
    public boolean updateAppointment(int appointmentId, AppointmentDTO appointmentDTO ) throws Exception {
            Connection conn = DBConnection.getInstance().getConnection();

            try {
                conn.setAutoCommit(false);

                boolean isUpdated = CrudUtil.execute(
                        "UPDATE Appointment SET doc_id=?, patient_id=?, doc_charges=?, appointment_date=?, app_type=?, app_statues=? WHERE appointment_id=?",
                        appointmentDTO.getDoc_id(),
                        appointmentDTO.getPatient_id(),
                        appointmentDTO.getDoc_charges(),
                        appointmentDTO.getAppointment_date(),
                        appointmentDTO.getAppType(),
                        appointmentDTO.getAppStatus().name(),
                        appointmentId
                );

                if (!isUpdated) {
                    throw new Exception("Failed to update appointment!!!!!!!!!!!!!!!");
                }

                if (appointmentDTO.getAppType().equals("Medication")) {

                    appTreatmentModel.deleteTreatmentbyAppId(appointmentId);

                } else {

                    appTreatmentModel.deleteTreatmentbyAppId(appointmentId);

                    // insert updated treatments
                    if (appointmentDTO.getTreatmentList() != null &&
                            !appointmentDTO.getTreatmentList().isEmpty()) {


                        appTreatmentModel.saveAppTreatment(
                                appointmentId,
                                appointmentDTO.getAppointment_date(),
                                appointmentDTO.getTreatmentList()
                        );
                    }
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

// this Method will delete the existing appointment
    public boolean deleteAppointment(String id) throws Exception {
        boolean result =
                CrudUtil.execute(
                        "DELETE FROM Appointment  WHERE appointment_id=? ",
                        id
                );
        return result;
    }

//  this Method will give the appointment_ID that new Appointment gonna assign-to
    public int  getNextAppId() throws SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT appointment_id FROM Appointment ORDER BY appointment_id DESC LIMIT  1 "

                );


        if (rs.next()) {
            int appointment_id = rs.getInt("appointment_id");
            System.out.println("getNextappointment_id: " + appointment_id);
            return appointment_id + 1;
        }

        // If table is empty
        return 1;

    }

// this Method will change the appointment_statue when consultation done
    public boolean changeAppStatusToConsulted( String id) throws Exception {

        boolean isConsultationDone = CrudUtil.execute(
                "UPDATE Appointment SET app_statues = ? WHERE appointment_id = ?",
                AppointmentStatus.CONSULTED.name(),
                id
        );
                return  isConsultationDone;
    }

// this Method will give number of active appointments in today Appointment list
    public int getNumOfActiveApp() throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT COUNT(*) AS total_active_appointments FROM Appointment " +
                        "WHERE appointment_date = ? " +
                        "  AND app_statues = 'ACTIVE'",
                today
        );
        if (rs.next()) {
            Integer totalActiveApp = rs.getInt("total_active_appointments");
            return totalActiveApp;
        }
        return 0;
    }

// this Method will give total number of appointments in today Appointment list
    public int getNumOfApp() throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT COUNT(*) AS total_appointments FROM Appointment WHERE appointment_date = ? ",
                today
        );
        if (rs.next()) {
            return rs.getInt("total_appointments");

        }
        return 0;
    }

//  this method will check patient appointment already exist
    public boolean isAppointmentExists(String pId , String date) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT patient_id FROM Appointment WHERE appointment_date = ?",
                pId,
                date
        );
        return rs.next(); // true if patient exists
    }
}
