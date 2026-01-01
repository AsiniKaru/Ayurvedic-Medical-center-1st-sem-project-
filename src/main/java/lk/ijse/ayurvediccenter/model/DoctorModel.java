package lk.ijse.ayurvediccenter.model;

import lk.ijse.ayurvediccenter.dto.DoctorDTO;
import lk.ijse.ayurvediccenter.dto.TreatmentDTO;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorModel {

    public String username;

//  this Method will give the logged doctor details from the User table using the user_id
    public DoctorDTO getDoctorId(int userId) throws SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT doc_id ,username FROM User WHERE emp_id is null and user_id=? ",
                        userId
                );

        if (rs.next()){
            int doc_id = rs.getInt("doc_id");
            username = rs.getString("username");

            return new DoctorDTO(doc_id);
        }else {
            return null;
        }

    }

//  this Method will give Doctor Details of a specific doctor_id
    public DoctorDTO searchDoctor(int id) throws SQLException {

        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM Doctor WHERE doc_id=? ",
                        id
                );

        if (rs.next()){
            int doc_id = rs.getInt("doc_id");
            String fName = rs.getString("doc_name");
            String lName = rs.getString("lName");
            String address = rs.getString("address");
            String contact_num = rs.getString("contact_num");
            String email = rs.getString("email");
            double dcharge = rs.getDouble("doc_charges");



            return new DoctorDTO(doc_id,fName,lName,address,contact_num,email,dcharge);
        }else {
            return null;
        }
    }

//  this Method will give the all the Doctor details of the table
    public List<DoctorDTO> getDoctors() throws SQLException {

        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM Doctor ORDER BY doc_id DESC"
                );

        List<DoctorDTO> doctorList = new ArrayList<>();

        while (rs.next()) {
            DoctorDTO doctorDTO = new DoctorDTO(
                    rs.getInt("doc_id"),
                    rs.getString("doc_fName"),
                    rs.getString("doc_lName"),
                    rs.getString("address"),
                    rs.getString("contact_num"),
                    rs.getString("email"),
                    rs.getDouble("doc_charges")
            );
            System.out.println(doctorDTO);
            doctorList.add(doctorDTO);
        }

        return doctorList;

    }

//   this Method will give the charges of a specific doctor_id
    public double getDoctorCharges(int id) throws SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT doc_charges FROM Doctor WHERE doc_id=? ",
                        id
                );

        if (rs.next()){

            return rs.getDouble("doc_charges");
        }else {
            return 0.00;
        }
    }

//   this Method will doctor name of a specific doctor_id
    public String getDoctorName(String id) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT doc_fName , doc_lName FROM Doctor WHERE doc_id=? ",
                id
        );
        if (rs.next()){
            String fName = rs.getString("doc_fName");
            String lName = rs.getString("doc_lName");

            return fName+" "+lName;

        }
        return null;
    }

//  this Method will give doctor id  of a specific chanelling Id
    public int getDocId(String id) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT doc_id FROM Appointment WHERE appointment_id=? ",
                id
        );
        if (rs.next()){
            int doc_id = rs.getInt("doc_id");
            return doc_id;
        }

                return 0;
    }

}
