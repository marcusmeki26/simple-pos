package Services;

import Database.DBConnection;

import java.sql.*;

public class LoginService {
    public static boolean isValidUser(String username, String password){
        try{
            Connection conn = DBConnection.getConnection();

            String selectQuery = "SELECT * FROM users_tbl WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(selectQuery);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }catch (SQLException sqle){
            System.out.println("Unable to Select or Retreive User");
            return false;
        }
    }
}
