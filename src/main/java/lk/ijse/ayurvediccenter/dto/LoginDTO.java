

package lk.ijse.ayurvediccenter.dto;

import lk.ijse.ayurvediccenter.model.enums.UserRole;

public class LoginDTO {
    private int userId;
    private String username;
    private String password;
    private UserRole role;

    public LoginDTO() {
    }

    public LoginDTO(int userId) {
        this.userId = userId;
    }

    public LoginDTO(String username) {
        this.username = username;
    }

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDTO(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public LoginDTO(int userId, String username,String password, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserRole getRole() { return role; }

    @Override
    public String toString() {
        return "LoginDTO{" + "username=" + username + ", password=" + password + '}';
    }
    
    
}
