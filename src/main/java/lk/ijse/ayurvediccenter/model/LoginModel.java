
package lk.ijse.ayurvediccenter.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import lk.ijse.ayurvediccenter.dto.LoginDTO;
import lk.ijse.ayurvediccenter.model.enums.UserRole;
import lk.ijse.ayurvediccenter.util.CrudUtil;

public class LoginModel {
    
    public LoginDTO verifyUser( String username, String password) throws SQLException{

        ResultSet rs = CrudUtil.execute(
                "SELECT u.user_id, u.username, u.password, u.emp_id, u.doc_id, e.role " +
                        "FROM User u " +
                        "LEFT JOIN Employee e ON u.emp_id = e.emp_id " +
                        "WHERE u.username = ?",
                username
        );

        if (!rs.next()) return null;

        if (!password.equals(rs.getString("password"))) return null;

        UserRole role;
        if (rs.getInt("doc_id") != 0) {
            role = UserRole.DOCTOR;
        }
        else {
            role = UserRole.valueOf(rs.getString("role").toUpperCase());
        }

        return new LoginDTO(
                rs.getInt("user_id"),
                rs.getString("username"),
                rs.getString("password"),
                role
        );
    }


   public LoginDTO verifyPassword(int user_id ,String username, String password) throws SQLException{

            ResultSet rs  =
                    CrudUtil.execute(
                            "SELECT user_id, username , password FROM User WHERE user_id = ?" ,
                            user_id
                );


        if(rs.next()){
            int userId = rs.getInt("user_id");
            String realUsername = rs.getString("username");
            String realPassword = rs.getString("password");

            if((!password.equals(realPassword))|| (!username.equals(realUsername)) ){
                return null;
            }

            return  new  LoginDTO(userId ,username, realPassword);

        }else{
            return null;
        }
    }


    public boolean updatePassword(int emp_id ,int User_id, String username, String password) throws SQLException{

        boolean result =
                CrudUtil.execute(
                        "UPDATE User SET  emp_id=? , username=? , password=? WHERE user_id=? ",
                        emp_id,
                        username,
                        password,
                        User_id

                );
        return result;
    }

}
