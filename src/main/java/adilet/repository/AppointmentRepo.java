package adilet.repository;

import adilet.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    @Query("select a from Appointment a where a.hospital.id = :id")
    List<Appointment> findAllByHospitalId(Long id);

    @Query("select a from Appointment a where a.hospital.id = :hospitalId and a.id = :appId")
    Appointment findAppointmentByHospitalId(Long hospitalId, Long appId);
}
