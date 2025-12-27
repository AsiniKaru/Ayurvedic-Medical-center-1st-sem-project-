package lk.ijse.ayurvediccenter.dto;

public class EmployeeDTO {
    private int emp_id ;
    private String fName;
    private String lName;
    private String address;
    private String contact_num;
    private String email;
    private String role;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int emp_id) {
        this.emp_id = emp_id;
    }

    public EmployeeDTO(String fName, String lName, String address, String contact_num, String email, String role) {
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.contact_num = contact_num;
        this.email = email;
        this.role = role;
    }

    public EmployeeDTO(int emp_id, String fName, String lName, String address, String contact_num, String email, String role) {
        this.emp_id = emp_id;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.contact_num = contact_num;
        this.email = email;
        this.role = role;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_num() {
        return contact_num;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "emp_id=" + emp_id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", address='" + address + '\'' +
                ", contact_num='" + contact_num + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
