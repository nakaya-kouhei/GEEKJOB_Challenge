package base;
/**
 *
 * @author hayashi-s
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBManager {
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //セキュリティ上パスワードは伏字
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","NAKAYA","teranoide0");
            System.out.println("DBConnected!!");
            return con;
        }catch(ClassNotFoundException e){
            throw new IllegalMonitorStateException();
        }catch (SQLException e){
            throw new IllegalMonitorStateException();
        }
    }
}
