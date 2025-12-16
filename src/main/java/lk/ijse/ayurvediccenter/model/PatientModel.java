package lk.ijse.ayurvediccenter.model;


import lk.ijse.ayurvediccenter.controller.LoginController;
import lk.ijse.ayurvediccenter.dto.LoginDTO;
import lk.ijse.ayurvediccenter.dto.PatientDTO;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientModel {

  public boolean savePatient (PatientDTO patientDTO) throws SQLException {
      boolean result =
              CrudUtil.execute(
                      "INSERT INTO Patient (user_id , patient_name , patient_nic,contact_num , address , gender, date_of_birth ) VALUES (?,?,?,?,?,?,?)",
                      LoginController.userId,
                      patientDTO.getPatientName(),
                      patientDTO.getNic(),
                      patientDTO.getContact(),
                      patientDTO.getAddress(),
                      patientDTO.getGender(),
                      patientDTO.getDateOfBirth()
              );
      return result;
  }



  public boolean updatePatient(PatientDTO patientDTO) throws SQLException {
     boolean result =
        CrudUtil.execute(
                "UPDATE Patient SET patient_name=? ,address=? ,patient_nic=? ,contact_num=?  , gender=?, date_of_birth=? WHERE patient_id=? ",
                patientDTO.getPatientName(),
                patientDTO.getAddress(),
                patientDTO.getNic(),
                patientDTO.getContact(),
                patientDTO.getGender(),
                patientDTO.getDateOfBirth(),
                patientDTO.getPatientId()
        );
     return result;
  }

  public void deletePatient(){}

  public PatientDTO searchPatient(String id) throws SQLException {
      ResultSet rs =
              CrudUtil.execute(
                      "SELECT * FROM Patient WHERE patient_id=? ",
                      id
              );

                if (rs.next()){
                    int patient_id = rs.getInt("patient_id");
                    String patient_name = rs.getString("patient_name");
                    String address = rs.getString("address");
                    String patient_nic = rs.getString("patient_nic");
                    String contact_num = rs.getString("contact_num");
                    String gender = rs.getString("gender");
                    String date_of_birth = rs.getString("date_of_birth");

                    return new PatientDTO(patient_id,patient_name,address,patient_nic,contact_num,gender,date_of_birth);
                }else {
                    return null;
                }
  }

    public PatientDTO searchPatientByName(String name) throws SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM Patient WHERE patient_name=? ",
                        name
                );

        if (rs.next()){
            int patient_id = rs.getInt("patient_id");
            String patient_name = rs.getString("patient_name");
            String address = rs.getString("address");
            String patient_nic = rs.getString("patient_nic");
            String contact_num = rs.getString("contact_num");
            String gender = rs.getString("gender");
            String date_of_birth = rs.getString("date_of_birth");

            return new PatientDTO(patient_id,patient_name,address,patient_nic,contact_num,gender,date_of_birth);
        }else {
            return null;
        }
    }



public List<PatientDTO> getPatients() throws SQLException {

    ResultSet rs =
            CrudUtil.execute(
                    "SELECT * FROM Patient ORDER BY patient_id DESC"
            );

    List<PatientDTO> patientList = new ArrayList<>();

    while (rs.next()) {
        PatientDTO patientDTO = new PatientDTO(
                rs.getInt("patient_id"),
                rs.getString("patient_name"),
                rs.getString("address"),
                rs.getString("patient_nic"),
                rs.getString("contact_num")
        );
        patientList.add(patientDTO);
    }

    return patientList;

}

}