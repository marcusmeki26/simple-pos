package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/simple_pos";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    public static Connection getConnection(){
        try{
            System.out.println("Connection Established !");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException sqle){
            System.out.println("Connection Error !");
            return null;
        }
    }
}
