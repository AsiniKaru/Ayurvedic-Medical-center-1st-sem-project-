package lk.ijse.ayurvediccenter.dto;

public class Employee {
    private int emp_id ;
    private String emp_name;
    private String contact_num;
    private String email;
    private String role;

    public Employee() {
    }

    public Employee(int emp_id, String emp_name, String contact_num, String email, String role) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.contact_num = contact_num;
        this.email = email;
        this.role = role;
    }

    public Employee(String emp_name, String contact_num, String email, String role) {
        this.emp_name = emp_name;
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

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
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
        return "Employee{" +
                "emp_id=" + emp_id +
                ", emp_name='" + emp_name + '\'' +
                ", contact_num='" + contact_num + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
