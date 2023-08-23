package adilet.api;

import adilet.entity.Hospital;
import adilet.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalApi {

    private final HospitalService hospitalService;

    @GetMapping( )
    public String getAllHospitals(Model model){
        model.addAttribute("getAllHospitals", hospitalService.getAllHospital()) ;
        return "hospitalHTML/getAll";
    }

    @GetMapping("/new")
    public String createHospital(Model model){
        model.addAttribute("newHospital", new Hospital());
        return "hospitalHTML/newHospital";
    }

    @PostMapping("/save")
    public String saveHospital(@ModelAttribute("newHospital") Hospital hospital){
        hospitalService.saveHospital(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("{hospitalId}/update")
    public String updateHospital(@PathVariable Long hospitalId, Model model){
        model.addAttribute("currentHospital", hospitalService.findById(hospitalId));
        return "hospitalHTML/updateHospital";
    }

    @PostMapping("{hospitalId}/edit")
    public String editHospital(@ModelAttribute("currentHospital") Hospital newHospital, @PathVariable Long hospitalId){
        hospitalService.updateHospital(hospitalId, newHospital);
        return "redirect:/hospitals";
    }

    @GetMapping("{hospitalId}/hospital")
    public String enterToHospital(@PathVariable("hospitalId") Long id){
        hospitalService.findById(id);
        return "hospitalHTML/hospital";
    }

    @GetMapping("{hospitalId}/delete")
    public String deleteHospitals(@PathVariable("hospitalId") Long id){
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals";
    }

}
