package adilet.service;

import adilet.entity.Department;
import adilet.entity.Hospital;
import adilet.entity.Patient;

import java.util.List;

public interface PatientService {
    void save(Patient patient);

    void createPatientByHospital(Patient patient, Long hospitalId);
    List<Patient> findAll();
    List<Patient> getAllPatients(Long id);
    Patient findPatientById(Long id);
    Patient findPatientByHospitalId(Long hospitalId, Long docId);
    void updateToPatientByHospitalId(Long hospitalId, Long dcoId, Patient patient);

    void deleteToPatientWithIdByHospitalId(Long hospitalId, Long docId);
}
