package ex2;

// loi: if (rs.next()) chỉ kiểm tra 1 lần duy nhất

// Cơ chế hoạt động của con trỏ ResultSet:
//Ban đầu, con trỏ nằm ở vị trí trước bản ghi đầu tiên (Before First).
//Mỗi khi gọi phương thức next(), con trỏ sẽ nhảy xuống dòng tiếp theo.
//Để in được toàn bộ danh sách, chúng ta cần một cấu trúc lặp (như ục while) để liên tgọi next().
// Vòng lặp sẽ tiếp diễn cho đến khi next() trả về false (nghĩa là đã đi hết danh sách).

import ex1.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PharmacyDAO {
    public void printPharmacyCatalogue() {
        String sql = "SELECT medicine_name, stock FROM Pharmacy";

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = (conn != null) ? conn.createStatement() : null;
                ResultSet rs = (stmt != null) ? stmt.executeQuery(sql) : null
        ) {
            if (conn == null || stmt == null || rs == null) {
                System.err.println("Không thể khởi tạo các tài nguyên kết nối.");
                return;
            }

            System.out.println("       DANH MỤC THUỐC TRONG KHO           ");
            System.out.printf("%-25s | %-10s%n", "Tên thuốc", "Số lượng");
            System.out.println("------------------------------------------");

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String name = rs.getString("medicine_name");
                int stock = rs.getInt("stock");
                System.out.printf("%-25s | %-10d%n", name, stock);
            }

            if (!hasData) {
                System.out.println("Thông báo: Hiện tại không có thuốc nào trong kho.");
            }
            System.out.println("==========================================");

        } catch (SQLException e) {
            System.err.println("Lỗi thực thi truy vấn SQL!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PharmacyDAO manager = new PharmacyDAO();
        manager.printPharmacyCatalogue();
    }

}
