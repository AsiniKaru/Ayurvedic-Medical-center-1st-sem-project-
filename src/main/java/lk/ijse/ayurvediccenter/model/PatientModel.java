package lk.ijse.ayurvediccenter.model;


import lk.ijse.ayurvediccenter.controller.LoginController;
import lk.ijse.ayurvediccenter.dto.PatientDTO;
import lk.ijse.ayurvediccenter.dto.tm.AppPatientTM;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientModel {

//  this Method will Save a new Patient to the Patient Table
    public boolean savePatient (PatientDTO patientDTO) throws SQLException {


          String patientSince = LocalDate.now().toString();

          boolean result =
                  CrudUtil.execute(
                          "INSERT INTO Patient (user_id , patient_fName ,patient_lName, patient_nic,contact_num , address , gender, date_of_birth , patient_since ) VALUES (?,?,?,?,?,?,?,?,?)",
                          LoginController.userId,
                          patientDTO.getFirstName(),
                          patientDTO.getLastName(),
                          patientDTO.getNic(),
                          patientDTO.getContact(),
                          patientDTO.getAddress(),
                          patientDTO.getGender(),
                          patientDTO.getDateOfBirth(),
                          patientSince
                  );
          return result;
      }

//  this Method will Update the Patient Table
    public boolean updatePatient(PatientDTO patientDTO) throws SQLException {
     boolean result =
        CrudUtil.execute(
                "UPDATE Patient SET patient_fName=? ,patient_lName=? ,address=? ,patient_nic=? ,contact_num=?  , gender=?, date_of_birth=? WHERE patient_id=? ",
                patientDTO.getFirstName(),
                patientDTO.getLastName(),
                patientDTO.getAddress(),
                patientDTO.getNic(),
                patientDTO.getContact(),
                patientDTO.getGender(),
                patientDTO.getDateOfBirth(),
                patientDTO.getPatientId()
        );
     return result;
  }

//  this Method will Delete the details from the table of a specific patient_id
    public boolean deletePatient(String id) throws SQLException {
        boolean result =
                CrudUtil.execute(
                        "DELETE FROM Patient  WHERE patient_id=? ",
                        id
                );
        return result;
    }

//   this method will Search Patient details of a specific patient_id
    public PatientDTO searchPatient(String id) throws SQLException {

      ResultSet rs =
              CrudUtil.execute(
                      "SELECT * FROM Patient WHERE patient_id=? ",
                      id
              );

                if (rs.next()){
                    int patient_id = rs.getInt("patient_id");
                    String patient_fName = rs.getString("patient_fName");
                    String patient_lName = rs.getString("patient_lName");
                    String patient_nic = rs.getString("patient_nic");
                    String contact_num = rs.getString("contact_num");
                    String address = rs.getString("address");
                    String gender = rs.getString("gender");
                    String date_of_birth = rs.getString("date_of_birth");


                    return new PatientDTO(patient_id,patient_fName,patient_lName,address,patient_nic,contact_num,gender,date_of_birth);
                }else {
                    return null;
                }
  }

//  this Method will Search a specific Patient details by patient_name
    public PatientDTO searchPatientByName(String name) throws SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM Patient WHERE patient_fName=? OR patient_lName=? ",
                        name,
                        name

                );

        if (rs.next()){
            int patient_id = rs.getInt("patient_id");
            String patient_fName = rs.getString("patient_fName");
            String patient_lName = rs.getString("patient_lName");
            String address = rs.getString("address");
            String patient_nic = rs.getString("patient_nic");
            String contact_num = rs.getString("contact_num");
            String gender = rs.getString("gender");
            String date_of_birth = rs.getString("date_of_birth");

            return new PatientDTO(patient_id,patient_fName,patient_lName,address,patient_nic,contact_num,gender,date_of_birth);
        }else {
            return null;
        }
    }

//  this Method will get the details of all the Patient of the table
    public List<PatientDTO> getPatients() throws SQLException {

    ResultSet rs =
            CrudUtil.execute(
                    "SELECT * FROM Patient ORDER BY patient_id DESC"
            );

    List<PatientDTO> patientList = new ArrayList<>();

    while (rs.next()) {
        PatientDTO patientDTO = new PatientDTO(
                rs.getInt("patient_id"),
                rs.getString("patient_fName"),
                rs.getString("patient_lName"),
                rs.getString("address"),
                rs.getString("patient_nic"),
                rs.getString("contact_num"),
                rs.getString("gender"),
                rs.getString("date_of_birth"),
                rs.getString("patient_since")

        );
        System.out.println(patientDTO);
        patientList.add(patientDTO);
    }

    return patientList;

}

//   this Method will give appointment details of a specific patient_id
    public List<AppPatientTM> getAppointmentById(String id) throws SQLException {

        ResultSet rs =
                CrudUtil.execute(
                        "SELECT a.appointment_id, p.patient_id, " +
                                "CONCAT(p.patient_fName, ' ', p.patient_lName) AS patient_name, " +
                                "p.contact_num, a.appointment_date, a.app_statues, a.app_type " +
                                "FROM Appointment a " +
                                "JOIN Patient p ON a.patient_id = p.patient_id " +
                                "WHERE p.patient_id = ? ",
                        id
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

//   this Method will give appointment details of a specific patient_fName or patient_lName
    public List<AppPatientTM> getAppointmentByName(String name) throws SQLException {

        ResultSet rs =
                CrudUtil.execute(
                        "SELECT a.appointment_id, p.patient_id, " +
                                "CONCAT(p.patient_fName, ' ', p.patient_lName) AS patient_name, " +
                                "p.contact_num, a.appointment_date, a.app_statues, a.app_type " +
                                "FROM Appointment a " +
                                "JOIN Patient p ON a.patient_id = p.patient_id " +
                                "WHERE p.patient_fName = ? Or p.patient_lName = ? ",
                        name,
                        name
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

// this Method will check if the patient already existing in the system
    public boolean isPatientExists (String  nic) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT patient_id FROM Patient WHERE patient_nic = ?",
                nic
        );
        return rs.next();
    }

}