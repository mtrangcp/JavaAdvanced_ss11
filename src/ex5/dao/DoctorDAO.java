package ex5.dao;

import ex1.DBConnection;
import ex5.model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public List<Doctor> getAllDoctors() {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Doctor(rs.getString("id"), rs.getString("name"), rs.getString("specialty")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean addDoctor(Doctor d) {
        String sql = "INSERT INTO Doctors (id, name, specialty) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, d.getId());
            pstmt.setString(2, d.getName());
            pstmt.setString(3, d.getSpecialty());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi DB: " + e.getMessage());
            return false;
        }
    }

    public void showSpecialtyStats() {
        String sql = "SELECT specialty, COUNT(*) as count FROM Doctors GROUP BY specialty";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("--- THỐNG KÊ---");
            while (rs.next()) {
                System.out.printf("Chuyên khoa: %-15s | Số lượng: %d%n", rs.getString("specialty"), rs.getInt("count"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }


}
