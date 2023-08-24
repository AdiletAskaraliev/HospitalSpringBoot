package adilet.service.impl;

import adilet.entity.Department;
import adilet.entity.Hospital;
import adilet.repository.DepartmentRepo;
import adilet.repository.HospitalRepo;
import adilet.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo departmentRepo;
    private final HospitalRepo hospitalRepo;

    @Override
    public void createDepartmentByHospital(Department department, Long hospitalId) {
        Hospital hospital = hospitalRepo.findById(hospitalId).orElseThrow();
        department.setHospital(hospital);
        departmentRepo.save(department);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public List<Department> getAllDepartments(Long id) {
        return findByHospitalId(id);
    }


    @Override
    public List<Department> findByHospitalId(Long hospitalId) {
        return departmentRepo.findDepartmentByHospitalId(hospitalId);
    }

    @Override
    public Hospital findById(Long id) {
        return hospitalRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hospital with ID " + id + " not found"));
    }

    @Override
    public Department findDepartmentById(Long id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hospital with ID " + id + " not found"));
    }


    @Override
    public List<Department> findAll(Long hospitalId) {
        return departmentRepo.findDepartmentByHospitalId(hospitalId);
    }

    @Override
    public Department findDepartmentByHospitalId(Long hospitalId, Long depId) {
        return departmentRepo.findDepartmentByHospitalIdAndId(hospitalId, depId);
    }

    @Override
    public void updateToDepartmentByHospitalId(Long hospitalId, Long depId, Department department) {
        Department department1 = departmentRepo.findDepartmentByHospitalIdAndId(hospitalId, depId);

        if (department1 != null) {
            department1.setName(department.getName());
            departmentRepo.save(department1);
        }
    }

    @Override
    public void deleteToDepartmentWithIdByHospitalId(Long hospitalId, Long depId) {
        Department departmentByHospitalIdAndId = departmentRepo.findDepartmentByHospitalIdAndId(hospitalId, depId);
        departmentRepo.delete(departmentByHospitalIdAndId);
    }


}
