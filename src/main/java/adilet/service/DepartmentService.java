package adilet.service;

import adilet.entity.Department;
import adilet.entity.Hospital;

import java.util.List;

public interface DepartmentService {
    void createDepartment(Department department);
    List<Department> findAll();
    List<Department> getAllDepartments(Long id);
    List<Department> findByHospitalId(Long hospitalId);
    Hospital findById(Long id);
    Department findDepartmentById(Long id);
    List<Department> findAll(Long hospitalId);
    Department findDepartmentByHospitalId(Long hospitalId, Long depId);
    void updateToDepartmentByHospitalId(Long hospitalId, Long depId, Department department);

    void deleteToDepartmentWithIdByHospitalId(Long hospitalId, Long depId);
}
