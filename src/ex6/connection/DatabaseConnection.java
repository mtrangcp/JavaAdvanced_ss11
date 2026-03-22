package ex6.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/MedicalAppointmentDB?createDatabaseIfNotExist=true";
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



}
