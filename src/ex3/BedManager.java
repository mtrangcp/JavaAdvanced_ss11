package ex3;

import ex1.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// Phương thức executeUpdate() trả về một số nguyên (int). Giá trị này đại diện cho số lượng hàng (rows)
// trong cơ sở dữ liệu thực sự bị tác động (thay đổi) bởi câu lệnh SQL (INSERT, UPDATE, hoặc DELETE)

// dùng kết quả phản hồi:
// Trường hợp thành công: Nếu giá trị trả về > 0, nghĩa là mã giường tồn tại và trạng thái đã được chuyển sang 'Đang sử dụng'.
// Trường hợp mã giường không tồn tại: Nếu giá trị trả về bằng 0, điều này khẳng định không có dòng nào
// trong bảng beds khớp với mã giường (ví dụ: Bed_999) mà giáo vụ đã nhập.

public class BedManager {
    public void updateBedStatus(String inputId) {
        String sql = "UPDATE Beds SET bed_status = 'Occupied' WHERE bed_id = '" + inputId + "'";

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = (conn != null) ? conn.createStatement() : null
        ) {
            if (stmt != null) {
                int rowsAffected = stmt.executeUpdate(sql);

                if (rowsAffected > 0) {
                    System.out.println("Thành công: Giường [" + inputId + "] đã được chuyển sang trạng thái 'Occupied'.");
                    System.out.println("Số lượng bản ghi đã thay đổi: " + rowsAffected);
                } else {
                    System.out.println("Thất bại: Mã giường [" + inputId + "] không tồn tại.");
                }
                System.out.println("-------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi hệ thống: Không thể kết nối hoặc thực thi câu lệnh SQL.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BedManager manager = new BedManager();

        System.out.println("Test1: ");
        manager.updateBedStatus("Bed_001");

        System.out.println("Test2: ");
        manager.updateBedStatus("Bed_999");
    }




}
