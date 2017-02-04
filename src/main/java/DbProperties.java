import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Anna on 02.02.2017.
 */
public class DbProperties {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/flats";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "anna4e";
    static Connection conn;
    public static void main(String[] args) throws SQLException {
         conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }

     public static Connection getConn() {
        return conn;
    }
}
