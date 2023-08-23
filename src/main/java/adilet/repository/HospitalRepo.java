package adilet.repository;

import adilet.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface HospitalRepo extends JpaRepository<Hospital, Long> {

    @Query("select h from Hospital h where h.id = :hospitalId")
    Hospital findHById(Long hospitalId);
}
