package adilet.service;

import adilet.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAllDoctors(Long hospitalId);
    void saveDoctorByHospitalId(Long hospitalId, Doctor doctor);
    void save(Doctor doctor);
    void delete(Long id);
    List<Doctor> getAll();
    Doctor getById(Long id);
    void update(Long id,Doctor doctor);
    public void assignDoctorToDepartment(Long doctorId, Long departmentId);
}
