package adilet.service.impl;

import adilet.entity.Department;
import adilet.entity.Doctor;
import adilet.entity.Hospital;
import adilet.repository.DepartmentRepo;
import adilet.repository.DoctorRepo;
import adilet.repository.HospitalRepo;
import adilet.service.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorsServiceImpl implements DoctorService {

    private final DoctorRepo doctorRepo;
    private final HospitalRepo hospitalRepo;
    private final DepartmentRepo departmentRepo;

    @Override
    public List<Doctor> findAllDoctors(Long hospitalId) {
        return doctorRepo.findAll(hospitalId);
    }

    @Override
    public void saveDoctorByHospitalId(Long hospitalId, Doctor doctor) {
        Hospital hospital = hospitalRepo.findById(hospitalId).orElseThrow();
        doctor.setHospital(hospital);
        doctorRepo.save(doctor);
    }

    @Override
    public void save(Doctor doctor) {
        doctorRepo.save(doctor);
    }

    @Override
    public void delete(Long id) {
        doctorRepo.deleteById(id);
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepo.findAll();
    }

    @Override
    public Doctor getById(Long id) {
        return doctorRepo.findById(id).orElseThrow();
    }

    @Override
    public void update(Long id, Doctor doctor) {
        Doctor doctor1 = doctorRepo.findById(id).get();
        doctor1.setFirstname(doctor.getFirstname());
        doctor1.setLastname(doctor.getLastname());
        doctor1.setPosition(doctor.getPosition());
        doctor1.setEmail(doctor.getEmail());
        doctorRepo.save(doctor1);
    }

    @Override
    public void assignDoctorToDepartment(Long doctorId, Long departmentId) {
        Doctor doctor = doctorRepo.findById(doctorId).orElse(null);
        Department department = departmentRepo.findById(departmentId).orElse(null);

        doctor.setDepartment(department);

        department.addDoctor(doctor);
    }
}
