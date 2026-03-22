package ex5.presentation;

import ex5.dao.DoctorDAO;
import ex5.model.Doctor;

import java.util.Scanner;

public class HospitalApp {
    private static final DoctorDAO doctorDAO = new DoctorDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== HỆ THỐNG QUẢN LÝ===");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ mới");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    doctorDAO.getAllDoctors().forEach(d ->
                            System.out.println(d.getId() + " - " + d.getName() + " [" + d.getSpecialty() + "]"));
                    break;
                case 2:
                    System.out.print("Nhập mã bác sĩ: "); String id = sc.nextLine();
                    System.out.print("Nhập họ tên: "); String name = sc.nextLine();
                    System.out.print("Nhập chuyên khoa: "); String spec = sc.nextLine();
                    if (doctorDAO.addDoctor(new Doctor(id, name, spec))) {
                        System.out.println("Thêm thành công!");
                    }
                    break;
                case 3:
                    doctorDAO.showSpecialtyStats();
                    break;
                case 4:
                    System.out.println("Thoat!");
                    break;
            }
        } while (choice != 4);


    }


}
