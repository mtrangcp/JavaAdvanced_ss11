package ex3;

import ex1.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
