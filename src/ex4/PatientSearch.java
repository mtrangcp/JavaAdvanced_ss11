package ex4;

import ex1.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Quá trình nối chuỗi (String Concatenation):
//Mã nguồn lỗi của bạn thực hiện cộng chuỗi trực tiếp:
//String sql = "SELECT * FROM patients WHERE name = '" + patientName + "'";
//Sau khi nối chuỗi, câu lệnh SQL gửi đến Database sẽ trở thành:
//SELECT * FROM patients WHERE name = '' OR '1'='1'

// heo quy tắc logic của SQL, khi sử dụng toán tử OR, chỉ cần một trong hai điều kiện đúng
// thì toàn bộ mệnh đề WHERE sẽ trả về kết quả TRUE mà 1 = 1 là điều luôn đúng.

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
