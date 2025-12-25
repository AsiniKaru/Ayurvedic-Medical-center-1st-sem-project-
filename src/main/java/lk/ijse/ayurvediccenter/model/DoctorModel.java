package lk.ijse.ayurvediccenter.model;

import lk.ijse.ayurvediccenter.dto.DoctorDTO;
import lk.ijse.ayurvediccenter.dto.EmployeeDTO;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorModel {

    public String username;

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
}
