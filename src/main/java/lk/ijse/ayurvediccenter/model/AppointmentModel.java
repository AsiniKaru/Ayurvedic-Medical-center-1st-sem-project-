package lk.ijse.ayurvediccenter.model;

import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel {

    //  this Method will get the details of all the (Today)Appointment of the table
    public List<AppPatientTM> getTodayAppointments() throws SQLException {

        String today = LocalDate.now().toString();

        ResultSet rs =
                CrudUtil.execute(
                        "SELECT a.appointment_id, p.patient_id, " +
                                "CONCAT(p.patient_fName, ' ', p.patient_lName) AS patient_name, " +
                                "p.contact_num, a.appointment_date, a.app_statues ,a.app_type" +
                                "FROM Appointment a JOIN Patient p ON a.patient_id = p.patient_id WHERE a.appointment_date =?",
                        today
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
//    public boolean saveAppointment (AppointmentDTO appointmentDTO) throws SQLException {
//
//        boolean result =
//                CrudUtil.execute(
//                        "INSERT INTO Appointment (doc_id , patient_id ,doc_charges , appointment_date ,app_type , app_statues ) VALUES (?,?,?,?,?,?,?,?,?)",
//
//
//
//                );
//        return result;
//    }


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
}
