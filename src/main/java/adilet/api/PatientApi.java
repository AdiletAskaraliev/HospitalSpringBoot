package adilet.api;

import adilet.entity.Department;
import adilet.entity.Patient;
import adilet.service.HospitalService;
import adilet.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientApi {

    private final PatientService patientService;
    private final HospitalService hospitalService;

    @GetMapping("/{hospitalId}")
    String getAllPatients(@PathVariable Long hospitalId, Model model){
        model.addAttribute("allPatients", patientService.getAllPatients(hospitalId));
        return "patientHTML/getAll";
    }

    @GetMapping("/create/{hospitalId}")
    String create(@PathVariable Long hospitalId, Model model){
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("allPatients", patientService.getAllPatients(hospitalId));
        model.addAttribute("newPatient", new Patient());
        return "patientHTML/create";
    }

    @PostMapping ("/save/{hospitalId}")
    String save(@ModelAttribute("newPatient") Patient patient,
                @PathVariable Long hospitalId){
        patientService.createPatientByHospital(patient, hospitalId);
        return "redirect:/patients/" + hospitalId;
    }


    @GetMapping("/{hospitalId}/update/{patId}")
    public String update(Model model,
                                                 @PathVariable Long hospitalId,
                                                 @PathVariable Long patId) {
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("patId", patId);
        model.addAttribute("thisPatient", patientService.findPatientByHospitalId(hospitalId, patId));
        return "patientHTML/update";
    }

    @PostMapping("/{hospitalId}/edit/{patId}")
    public String edit(@ModelAttribute("thisPatient") Patient newPatient,
                                                 @PathVariable Long hospitalId, @PathVariable Long patId) {
        patientService.updateToPatientByHospitalId(hospitalId, patId, newPatient);
        return "redirect:/patients/" + hospitalId;
    }

    @GetMapping("/{hospitalId}/delete/{patId}")
    public String delete(@PathVariable("hospitalId") Long hospitalId,
                                                 @PathVariable("patId") Long patId) {
        patientService.deleteToPatientWithIdByHospitalId(hospitalId, patId);
        return "redirect:/patients/" + hospitalId;
    }

}
