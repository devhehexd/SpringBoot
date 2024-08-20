package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {

    private static MysqlConnection mysqlConn = new MysqlConnection();
    private String url = "jdbc:mysql://localhost:3307/guestbook?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private String driver = "com.mysql.cj.jdbc.Driver";

    //싱글톤. 자원관리
    private MysqlConnection() {

    }

    public static MysqlConnection getInstance() {
        return mysqlConn;
    }

    public Connection getConnection() {

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, "root", "rlqja");
            return conn;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
