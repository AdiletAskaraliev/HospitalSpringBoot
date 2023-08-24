package adilet.service;

import adilet.entity.Appointment;
import adilet.entity.Patient;

import java.util.List;

public interface AppointmentService {

    void save(Appointment appointment);

    String createAppointmentByHospital(Appointment appointment, Long hospitalId);
    List<Appointment> findAll();
    List<Appointment> getAllAppointment(Long hosId);
    Appointment findAppointmentById(Long id);
    Appointment findAppointmentByHospitalId(Long hospitalId, Long appId);
    void updateToAppointmentByHospitalId(Long hospitalId, Long appId, Appointment appointment);

    void deleteToAppointmentWithIdByHospitalId(Long hospitalId, Long appId);
}
