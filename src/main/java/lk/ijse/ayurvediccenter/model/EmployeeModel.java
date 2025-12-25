package lk.ijse.ayurvediccenter.model;

import javafx.fxml.Initializable;
import lk.ijse.ayurvediccenter.dto.DoctorDTO;
import lk.ijse.ayurvediccenter.dto.EmployeeDTO;
import lk.ijse.ayurvediccenter.util.CrudUtil;
import lk.ijse.ayurvediccenter.controller.LoginController;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
    EmployeeDTO employeeDTO = new EmployeeDTO();
    DoctorDTO doctorDTO = new DoctorDTO();

    boolean isEmp;
    public String username;


    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException {
        boolean result =
                CrudUtil.execute(
                        "UPDATE Employee SET emp_name=? ,lName=? ,address=?  ,contact_num=? ,email=? , role=? WHERE emp_id=? ",
                        employeeDTO.getfName(),
                        employeeDTO.getlName(),
                        employeeDTO.getAddress(),
                        employeeDTO.getContact_num(),
                        employeeDTO.getEmail(),
                        employeeDTO.getRole(),
                        employeeDTO.getEmp_id()
                );
        return result;
    }

    public EmployeeDTO searchEmployee(int id) throws SQLException {

                ResultSet rs =
                        CrudUtil.execute(
                                "SELECT * FROM Employee WHERE emp_id=? ",
                                id
                        );

                if (rs.next()){
                    int emp_id = rs.getInt("emp_id");
                    String fName = rs.getString("emp_name");
                    String lName = rs.getString("lName");
                    String address = rs.getString("address");
                    String contact_num = rs.getString("contact_num");
                    String email = rs.getString("email");
                    String role = rs.getString("role");


                    return new EmployeeDTO(emp_id,fName,lName,address,contact_num,email,role);
                }else {
                    return null;
                }
    }

    public boolean getEmpState() throws SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM User WHERE user_id=? ",
                        LoginController.userId
                );

        if (rs.next()) {
            Integer emp_id = rs.getObject("emp_id", Integer.class);
            Integer doc_id = rs.getObject("doc_id", Integer.class);

                if (emp_id == null) {
                    doctorDTO.setDoctor_id(doc_id);
                    System.out.println("Doctor");
                    return isEmp = false;
                } else {
                    employeeDTO.setEmp_id(emp_id);
                    System.out.println("Employee");
                    return isEmp = true;
                }

            }else{
            System.out.println("null");
                return false;
            }

    }

    public EmployeeDTO getEmployeeId(int userId) throws SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM User WHERE user_id=? ",
                        userId
                );

        if (rs.next()){
            int emp_id = rs.getInt("emp_id");
            username = rs.getString("username");

            return new EmployeeDTO(emp_id);
        }else {
            return null;
        }
    }
}
