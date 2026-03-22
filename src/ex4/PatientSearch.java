package ex4;

import ex1.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientSearch {
    public String sanitizeInput(String input) {
        if (input == null) return "";

        return input.replace("'", "''")
                .replace("--", "")
                .replace(";", "");
    }

    public void searchPatient(String rawName) {
        String safeName = sanitizeInput(rawName);

        String sql = "SELECT * FROM Patients WHERE full_name = '" + safeName + "'";

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = (conn != null) ? conn.createStatement() : null;
        ) {
            if (stmt != null) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    System.out.println("--- KẾT QUẢ---");
                    boolean found = false;
                    while (rs.next()) {
                        found = true;
                        System.out.println("Bệnh nhân: " + rs.getString("full_name") +
                                " | Bệnh án: " + rs.getString("medical_history"));
                    }
                    if (!found) {
                        System.out.println("Không tìm thấy bệnh nhân phù hợp.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn hệ thống!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PatientSearch searcher = new PatientSearch();

        String hackerInput = "' OR '1'='1";

        searcher.searchPatient(hackerInput);
    }



}
