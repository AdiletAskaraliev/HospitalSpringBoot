package adilet.api;

import adilet.entity.Department;
import adilet.entity.Doctor;
import adilet.service.DepartmentService;
import adilet.service.DoctorService;
import adilet.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorsAPI {

    private final DoctorService doctorService;
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;


    @GetMapping("/{hospitalId}")
    String getAllDoctors(@PathVariable Long hospitalId, Model model){
        model.addAttribute("getAllDoctors", doctorService.findAllDoctors(hospitalId));
        return "doctorHTML/getAll";
    }

    @GetMapping("/create/{hospitalId}")
    String createDoctorByHospitalId(@PathVariable Long hospitalId, Model model){
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("newDoctor", new Doctor());
        return "doctorHTML/create";
    }

    @PostMapping("/save/{hospitalId}")
    String saveDoctorByHospitalId(@PathVariable("hospitalId") Long hospitalId,
                                  @ModelAttribute Doctor doctor){
        doctorService.saveDoctorByHospitalId(hospitalId, doctor);
        return "redirect:/doctors/" + hospitalId;
    }

    @GetMapping("/assign/{doctorId}")
    public String showAssignToDepartmentForm(@PathVariable Long doctorId, Model model) {
        List<Department> allDepartments = departmentService.findAll();
        model.addAttribute("allDepartments", allDepartments);
        model.addAttribute("doctorId", doctorId);
        return "doctorHTML/assignToDepartment";
    }

    @PostMapping("/{doctorId}/assign")
    String assignDoctorToDepartment(@PathVariable Long doctorId, Long departmentId) {
        doctorService.assignDoctorToDepartment(doctorId, departmentId);
        return "redirect:/doctors/" + doctorId;
    }

//    @GetMapping("/{hospitalId}/update/{depId}")
}
