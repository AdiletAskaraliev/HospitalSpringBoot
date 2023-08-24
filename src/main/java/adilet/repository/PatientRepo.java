package adilet.repository;


import adilet.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p where p.hospital.id = :id")
    List<Patient> findAllByHospitalId(Long id);

    @Query("select p from Patient p where p.hospital.id = :hospitalId and p.id = :docId")
    Patient findPatientByHospitalId(Long hospitalId, Long docId);
}
