package lk.ijse.ayurvediccenter.model;

import lk.ijse.ayurvediccenter.controller.LoginController;
import lk.ijse.ayurvediccenter.dto.LoginDTO;
import lk.ijse.ayurvediccenter.dto.PatientDTO;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.SQLException;

public class PatientModel {

  public boolean savePatient (PatientDTO patientDTO) throws SQLException {
      boolean result =
              CrudUtil.execute(
                      "INSERT INTO Patient (user_id , patient_name , patient_nic,contact_num , address , gender, date_of_birth ) VALUES (?,?,?,?,?,?,?)",
                      LoginController.userId,
                      patientDTO.getPatient_name(),
                      patientDTO.getPatient_nic(),
                      patientDTO.getContact_num(),
                      patientDTO.getAddress(),
                      patientDTO.getGender(),
                      patientDTO.getDate_of_birth()
              );
      return result;
  }

  public void updateCustomer(){}

  public void deletePatient(){}

  public void searchPatient(){}

  public void getPatient(){}
}
