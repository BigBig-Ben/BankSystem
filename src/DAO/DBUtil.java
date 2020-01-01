package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String URL="jdbc:mysql://localhost:3306/banksystem?serverTimezone=UTC";
    private static String username="root";
    private static String password="123456";

    public static Connection open()
    {
        try
        {
            Class.forName(driver);
            return DriverManager.getConnection(URL, username, password);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    public static void close(Connection conn)
    {
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
