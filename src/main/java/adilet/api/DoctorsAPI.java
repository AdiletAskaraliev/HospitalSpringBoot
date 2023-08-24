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


    @GetMapping("/{hospitalId}/assign/{doctorId}")
    public String showAssignToDepartmentForm(@PathVariable Long doctorId,
                                             @PathVariable Long hospitalId,
                                             Model model) {
        model.addAttribute("allDepartments", departmentService.findAll(hospitalId));
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("hospitalId", hospitalId);
        return "doctorHTML/assignToDepartment";
    }

    @PostMapping("/accept/{doctorId}")
    String assignDoctorToDepartment(@PathVariable Long doctorId, Long departmentId, Model model) {
        model.addAttribute("depId", departmentId);
        doctorService.assignDoctorToDepartment(doctorId, departmentId);
        return "redirect:/doctors/{hospitalId}";
    }



    @GetMapping("/{hospitalId}/update/{docId}")
    String updateDoctorByHospitalId(@PathVariable Long hospitalId,
                                    @PathVariable Long docId,
                                    Model model){
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("docId", docId);
        model.addAttribute("thisDoctor", doctorService.findDoctorByHospitalId(hospitalId, docId));
        return "doctorHTML/update";
    }

    @PostMapping("/{hospitalId}/edit/{docId}")
    String editDoctorByHospitalId(@PathVariable Long hospitalId,
                                  @PathVariable Long docId,
                                  @ModelAttribute("thisDoctor") Doctor newDoctor){
        doctorService.updateToDoctorByHospitalId(hospitalId, docId, newDoctor);
        return "redirect:/doctors/" + hospitalId;
    }

    @GetMapping("/{hospitalId}/delete/{docId}")
    String deleteDocByHospitalId(@PathVariable("hospitalId") Long hospitalId,
                                 @PathVariable("docId") Long docId){
        doctorService.deleteDoctorByHospitalId(hospitalId, docId);
        return "redirect:/doctors/" + hospitalId;

    }
}
