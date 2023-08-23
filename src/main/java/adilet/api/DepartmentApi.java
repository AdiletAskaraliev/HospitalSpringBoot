package adilet.api;

import adilet.entity.Department;
import adilet.service.DepartmentService;
import adilet.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentApi {

    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @GetMapping("/{hospitalId}")
    public String getAllDepartments(@PathVariable("hospitalId") Long hospitalId, Model model) {
        model.addAttribute("getAllDepartments", departmentService.findAll(hospitalId));
        return "departmentHTML/getAllD";
    }

    @GetMapping("/create/{hospitalId}")
    public String createDepartmentByHospitalId(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("newDepartment", new Department());
        return "departmentHTML/createD";
    }

//@GetMapping("/createD")
//public String createDepartmentByHospitalId(Model model) {
//    model.addAttribute("getAllHospitals", hospitalService.getAllHospital());
//    model.addAttribute("newDepartment", new Department());
//    return "departmentHTML/createD";
//}

    @PostMapping("/saveD")
    public String saveDepartmentByHospital(@ModelAttribute("newDepartment") Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "redirect:/departments";
        }
        departmentService.createDepartment(department);
        return "redirect:/hospitals";
    }

    @GetMapping("/{hospitalId}/enter/{depId}")
    public String enterToDepartmentByHospitalId(@PathVariable Long hospitalId, @PathVariable Long depId) {
        departmentService.findDepartmentByHospitalId(hospitalId, depId);
        return "departmentHTML/departmentPage";
    }

    @GetMapping("/{hospitalId}/update/{depId}")
    public String updateToDepartmentByHospitalId(Model model, @PathVariable Long hospitalId, @PathVariable Long depId) {
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("depId", depId);
        model.addAttribute("thisDepartment", departmentService.findDepartmentByHospitalId(hospitalId, depId));
        return "departmentHTML/updateDepartment";
    }

    @PostMapping("/{hospitalId}/edit/{depId}")
    public String updateToDepartmentByHospitalId(@ModelAttribute("thisDepartment") Department newDepartment,
                                                 @PathVariable Long hospitalId, @PathVariable Long depId) {
        departmentService.updateToDepartmentByHospitalId(hospitalId, depId, newDepartment);
        return "redirect:/departments/" + hospitalId;
    }

    @GetMapping("/{hospitalId}/delete/{depId}")
    public String deleteToDepartmentByHospitalId(@PathVariable("hospitalId") Long hospitalId, @PathVariable("depId") Long depId) {
        departmentService.deleteToDepartmentWithIdByHospitalId(hospitalId, depId);
        return "redirect:/departments/" + hospitalId;
    }


//    @GetMapping("/departments/{hospitalId}")
//    public String goBackToDepartment(@PathVariable("hospitalId") Long hospitalId) {
//        departmentService.findById(hospitalId);
//        return "redirect:/departments/" + hospitalId;
//    }

//    @GetMapping("/{hospitalId}/hospital")
//    public String goBackToHospital(@PathVariable("hospitalId") Long hospitalId) {
//        departmentService.findById(hospitalId);
//        return "redirect:hospitalHTML/hospital";
//    }
}

