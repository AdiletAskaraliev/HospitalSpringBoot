package adilet.service.impl;

import adilet.entity.Department;
import adilet.entity.Hospital;
import adilet.entity.Patient;
import adilet.repository.HospitalRepo;
import adilet.repository.PatientRepo;
import adilet.service.PatientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepo patientRepo;
    private final HospitalRepo hospitalRepo;

    @Override
    public void save(Patient patient) {
        patientRepo.save(patient);
    }

    @Override
    public void createPatientByHospital(Patient patient, Long hospitalId) {
        Hospital hospital = hospitalRepo.findById(hospitalId).orElseThrow();
        patient.setHospital(hospital);
        patientRepo.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    @Override
    public List<Patient> getAllPatients(Long id) {
        return patientRepo.findAllByHospitalId(id);
    }

    @Override
    public Patient findPatientById(Long id) {
        return patientRepo.findById(id).orElseThrow();
    }

    @Override
    public Patient findPatientByHospitalId(Long hospitalId, Long docId) {
        return patientRepo.findPatientByHospitalId(hospitalId, docId);
    }

    @Override
    public void updateToPatientByHospitalId(Long hospitalId, Long docId, Patient patient) {
        Patient patient1 = findPatientByHospitalId(hospitalId, docId);
        if (patient1 != null){
            patient1.setFirstname(patient.getFirstname());
            patient1.setLastname(patient.getLastname());
            patient1.setGender(patient.getGender());
            patient1.setPhoneNumber(patient.getPhoneNumber());
            patient1.setEmail(patient.getEmail());

            patientRepo.save(patient1);
        }
    }

    @Override
    public void deleteToPatientWithIdByHospitalId(Long hospitalId, Long docId) {
        Patient patient = findPatientByHospitalId(hospitalId, docId);
        patientRepo.delete(patient);
    }
}
