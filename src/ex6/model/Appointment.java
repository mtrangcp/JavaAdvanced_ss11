package ex6.model;

import java.util.Date;

public class Appointment {
    private int id;
    private String patientName;
    private Date appointmentDate;
    private String doctorName;
    private String status;

    public Appointment(String patientName, Date appointmentDate, String doctorName, String status) {
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.doctorName = doctorName;
        this.status = status;
    }

    public Appointment(int id, String patientName, Date appointmentDate, String doctorName, String status) {
        this.id = id;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.doctorName = doctorName;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public Date getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(Date appointmentDate) { this.appointmentDate = appointmentDate; }
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("ID: %d | BN: %-15s | Ngày: %s | BS: %-15s | Trạng thái: %s",
                id, patientName, appointmentDate, doctorName, status);
    }


}
