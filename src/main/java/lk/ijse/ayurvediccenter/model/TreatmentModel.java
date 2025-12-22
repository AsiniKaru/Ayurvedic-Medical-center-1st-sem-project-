package lk.ijse.ayurvediccenter.model;


import lk.ijse.ayurvediccenter.dto.TreatmentDTO;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TreatmentModel {

    public boolean saveTreatment (TreatmentDTO treatmentDTO) throws SQLException {

        boolean result =
                CrudUtil.execute(
                        "INSERT INTO Treatment ( treatment_name, treatment_type ,description , treatment_charges) VALUES (?,?,?,?)",
                        treatmentDTO.getName(),
                        treatmentDTO.getType(),
                        treatmentDTO.getDescription(),
                        treatmentDTO.getPrice()
                );
        return result;
    }


/*
    public boolean updateTreatment(TreatmentDTO treatmentDTO) throws SQLException {
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

    public void deleteTreatment(){}
*/
    public TreatmentDTO searchTreatment(String id) throws SQLException {


        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM Treatment WHERE treatment_id=? ",
                        id
                );

        if (rs.next()){
            int treatment_id = rs.getInt("treatment_id");
            String treatment_Name = rs.getString("treatment_name");
            String treatment_type = rs.getString("treatment_type");
            String description = rs.getString("description");
            double price = rs.getDouble("treatment_charges");


            return new TreatmentDTO(treatment_id,treatment_Name,treatment_type,description,price);
        }else {
            return null;
        }
    }

    public TreatmentDTO searchTreatmentByName(String name) throws SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM Treatment WHERE treatment_name=?  ",
                        name

                );

        if (rs.next()){
            int treatment_id = rs.getInt("treatment_id");
            String treatment_Name = rs.getString("treatment_name");
            String treatment_type = rs.getString("treatment_type");
            String description = rs.getString("description");
            double price = rs.getDouble("treatment_charges");


            return new TreatmentDTO(treatment_id,treatment_Name,treatment_type,description,price);
        }else {
            return null;
        }
    }



    public List<TreatmentDTO> getTreatments() throws SQLException {

        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM Treatment ORDER BY treatment_id DESC"
                );

        List<TreatmentDTO> treatmentList = new ArrayList<>();

        while (rs.next()) {
            TreatmentDTO treatmentDTO = new TreatmentDTO(
                    rs.getInt("treatment_id"),
                    rs.getString("treatment_name"),
                    rs.getString("treatment_type"),
                    rs.getString("description"),
                    rs.getDouble("treatment_charges")
            );
            System.out.println(treatmentDTO);
            treatmentList.add(treatmentDTO);
        }

        return treatmentList;

    }

}
