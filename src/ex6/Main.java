package ex6;

import ex6.model.Appointment;
import ex6.repository.AppointmentRepository;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AppointmentRepository repo = new AppointmentRepository();

        System.out.println("--- Đang thêm dữ liệu mẫu ---");
        repo.addAppointment(new Appointment("Nguyen Van A", Date.valueOf("2026-03-25"), "Dr. Strange", "Scheduled"));
        repo.addAppointment(new Appointment("Tran Thi B", Date.valueOf("2026-04-10"), "Dr. House", "Confirmed"));

        System.out.println("\n--- Danh sách lịch khám ---");
        List<Appointment> list = repo.getAllAppointments();
        list.forEach(System.out::println);

        if (!list.isEmpty()) {
            Appointment firstApp = list.get(0);
            firstApp.setStatus("Completed");
            repo.updateAppointment(firstApp);
        }

        repo.deleteAppointment(2);

        System.out.println("\n--- Danh sách sau chỉnh sửa ---");
        repo.getAllAppointments().forEach(System.out::println);


    }
}