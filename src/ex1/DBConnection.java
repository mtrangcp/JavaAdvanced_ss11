package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// loi code cu:
// - chua dong ket noi gay lang phi tai nguyen -> treo he thong
// - connection cu bi timeout -> Communications link failure

public class DBConnection {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB?createDatabaseIfNotExist=true";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            System.err.println("Chua cai dat driver");
        } catch (SQLException e) {
            System.err.println("Loi SQL: ket noi that bai");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = getConnection();
            System.out.println(conn);
        }finally {
            try {
                if  (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
