package adilet.service.impl;

import adilet.entity.*;
import adilet.repository.*;
import adilet.service.AppointmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepo appointmentRepo;

    private final HospitalRepo hospitalRepo;
    private final DepartmentRepo departmentRepo;
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;

    @Override
    public void save(Appointment appointment) {
        appointmentRepo.save(appointment);
    }

    @Override
    public String createAppointmentByHospital(Appointment appointment, Long hospitalId) {

        Hospital hospital = hospitalRepo.findById(hospitalId).orElseThrow(()->new NullPointerException("hospital is not found"));
        Department department = departmentRepo.findById(appointment.getDepartment().getId()).orElseThrow(()->new NullPointerException("department is not found"));
        Doctor doctor = doctorRepo.findById(appointment.getDoctor().getId()).orElseThrow(()->new NullPointerException("doctor id not found"));
        Patient patient = patientRepo.findById(appointment.getPatient().getId()).orElseThrow(()-> new NullPointerException("patient is not found"));
        appointment.setHospital(hospital);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDepartment(department);
        hospital.getAppointments().add(appointment);
        appointmentRepo.save(appointment);

        return "Department is saved!";
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepo.findAll();
    }

    @Override
    public List<Appointment> getAllAppointment(Long hosId) {
        return appointmentRepo.findAllByHospitalId(hosId);
    }

    @Override
    public Appointment findAppointmentById(Long id) {
        return appointmentRepo.findById(id).orElseThrow();
    }

    @Override
    public Appointment findAppointmentByHospitalId(Long hospitalId, Long appId) {
        return appointmentRepo.findAppointmentByHospitalId(hospitalId, appId);
    }

    @Override
    public void updateToAppointmentByHospitalId(Long hospitalId, Long appId, Appointment appointment) {
        Appointment appointment1 = appointmentRepo.findAppointmentByHospitalId(hospitalId, appId);

        appointment1.setDate(appointment.getDate());
        appointment1.setPatient(appointment.getPatient());
        appointment1.setDoctor(appointment.getDoctor());
        appointment1.setDepartment(appointment.getDepartment());

        appointmentRepo.save(appointment1);
    }

    @Override
    public void deleteToAppointmentWithIdByHospitalId(Long hospitalId, Long appId) {
        Appointment appointment = appointmentRepo.findAppointmentByHospitalId(hospitalId, appId);
        appointmentRepo.delete(appointment);
    }
}
