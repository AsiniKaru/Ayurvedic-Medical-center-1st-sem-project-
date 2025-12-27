package lk.ijse.ayurvediccenter.model;

import lk.ijse.ayurvediccenter.dto.DoctorDTO;
import lk.ijse.ayurvediccenter.dto.EmployeeDTO;
import lk.ijse.ayurvediccenter.util.CrudUtil;
import lk.ijse.ayurvediccenter.controller.LoginController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    EmployeeDTO employeeDTO = new EmployeeDTO();
    DoctorDTO doctorDTO = new DoctorDTO();

    boolean isEmp;
    public String username;

//    this Method will Update the Employee Table
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException {
        boolean result =
                CrudUtil.execute(
                        "UPDATE Employee SET emp_name=? ,lName=? ,address=?  ,contact_num=? ,email=? , role=? WHERE emp_id=? ",
                        employeeDTO.getFName(),
                        employeeDTO.getLName(),
                        employeeDTO.getAddress(),
                        employeeDTO.getContact_num(),
                        employeeDTO.getEmail(),
                        employeeDTO.getRole(),
                        employeeDTO.getEmp_id()
                );
        return result;
    }

//    this Method will search Employee Details of a specific emp_id
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

//    this Method will give logged user whether a Doctor or a employee
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

//    this Method will give the emp_id of the user
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

//    this Method will give details of the all Employees
    public List<EmployeeDTO> getEmployees() throws  SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM Employee ORDER BY emp_id DESC"
                );

        List<EmployeeDTO> employeeList = new ArrayList<>();

        while (rs.next()) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    rs.getInt("emp_id"),
                    rs.getString("emp_name"),
                    rs.getString("lName"),
                    rs.getString("address"),
                    rs.getString("contact_num"),
                    rs.getString("email"),
                    rs.getString("role")
            );
            System.out.println(employeeDTO);
            employeeList.add(employeeDTO);
        }

        return employeeList;

    }

//    this Method will delete the details of a specific emp_id
    public boolean deleteEmployee(String id) throws SQLException {
        boolean result =
                CrudUtil.execute(
                        "DELETE FROM Employee  WHERE emp_id=? ",
                        id
                );
        return result;

    }

//    this Method will save a new employee to the Employee table
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException {

        boolean result =
                CrudUtil.execute(
                        "INSERT INTO Employee ( emp_id ,emp_name ,lName, address ,contact_num , email , role ) VALUES (?,?,?,?,?,?,?)",
                        employeeDTO.getEmp_id(),
                        employeeDTO.getFName(),
                        employeeDTO.getLName(),
                        employeeDTO.getAddress(),
                        employeeDTO.getContact_num(),
                        employeeDTO.getEmail(),
                        employeeDTO.getRole()

                );
        return result;
    }

//    this Method will give details of a specific emp_name
    public EmployeeDTO searchEmployeeByName(String name) throws SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM Employee WHERE emp_name=? OR lName=? ",
                        name,
                        name

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


    //    this method will give the next emp_id that the new Employee gonna assign-to
    public int  getNextEmpId() throws SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT emp_id FROM Employee ORDER BY emp_id DESC LIMIT  1 "

                );


        if (rs.next()) {
            int emp_id = rs.getInt("emp_id");
            System.out.println("getNextEmpId: " + emp_id);
            return emp_id + 1;
        }

        // If table is empty
        return 0;

    }
}
