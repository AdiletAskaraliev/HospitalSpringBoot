package adilet.repository;

import adilet.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

    @Query("select d from Department d where d.hospital.id = :hospitalId")
    List<Department> findDepartmentByHospitalId(Long hospitalId);

    @Query("select d from Department d where d.hospital.id = :hospitalId and d.id = :depId")
    Department findDepartmentByHospitalIdAndId(Long hospitalId, Long depId);

}
