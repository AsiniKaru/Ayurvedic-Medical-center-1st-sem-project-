
package lk.ijse.ayurvediccenter.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import lk.ijse.ayurvediccenter.dto.LoginDTO;
import lk.ijse.ayurvediccenter.util.CrudUtil;

public class LoginModel {
    
    public LoginDTO verifyUser(String username, String password) throws SQLException{
        
        ResultSet rs  = 
               CrudUtil.execute(
               "SELECT * FROM `User` WHERE username = ?" ,
               username
               );
          
       
        if(rs.next()){
            username = rs.getString("username");
            String realPassword = rs.getString("password");
            
            if(!password.equals(realPassword)){
                        return null;
            }
          
            return new LoginDTO(username, realPassword);
            
        }else{
            return null;
        }
    }
    
    
}
