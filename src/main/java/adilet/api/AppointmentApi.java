package adilet.api;

import adilet.entity.Appointment;
import adilet.entity.Department;
import adilet.entity.Doctor;
import adilet.entity.Patient;
import adilet.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentApi {

    private final AppointmentService appointmentService;
    private final HospitalService hospitalService;
    private final DoctorService doctorService;
    private  final PatientService patientService;
    private final DepartmentService departmentService;



    @GetMapping("/{hospitalId}")
    String getAll(@PathVariable Long hospitalId, Model model){
        model.addAttribute("allAppointments", appointmentService.getAllAppointment(hospitalId));
        return "appointment/getAll";
    }

    @GetMapping("/create/{hospitalId}")
    String createDoctorByHospitalId(@PathVariable Long hospitalId, Model model){
        List<Doctor> doctors = doctorService.findAllDoctors(hospitalId);
        List<Patient> patients = patientService.getAllPatients(hospitalId);
        List<Department> departments = departmentService.getAllDepartments(hospitalId);

        model.addAttribute("patients", patients);
        model.addAttribute("doctors", doctors);
        model.addAttribute("departments", departments);
        model.addAttribute("newAppointment", new Appointment());

        return "appointment/create";
    }


    @PostMapping("/save/{hospitalId}")
    public String saveAppointment(@ModelAttribute("newAppointment")Appointment appointment,
                                  @PathVariable Long hospitalId) {
        appointmentService.createAppointmentByHospital(appointment, hospitalId);
        return "redirect:/appointments/"+hospitalId;
    }



    @GetMapping("/{hospitalId}/update/{appId}")
    String updateDoctorByHospitalId(@PathVariable Long hospitalId,
                                    @PathVariable Long appId,
                                    Model model){
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("docId", appId);
        model.addAttribute("thisAppointment", appointmentService.findAppointmentByHospitalId(hospitalId, appId));
        return "appointment/update";
    }

    @PostMapping("/{hospitalId}/edit/{appId}")
    String editDoctorByHospitalId(@PathVariable Long hospitalId,
                                  @PathVariable Long appId,
                                  @ModelAttribute("thisAppointment") Appointment appointment){
        appointmentService.updateToAppointmentByHospitalId(hospitalId, appId, appointment);
        return "redirect:/appointments/" + hospitalId;
    }

    @GetMapping("/{hospitalId}/delete/{appId}")
    String deleteDocByHospitalId(@PathVariable("hospitalId") Long hospitalId,
                                 @PathVariable("appId") Long appId){
        appointmentService.deleteToAppointmentWithIdByHospitalId(hospitalId, appId);
        return "redirect:/appointments/" + hospitalId;

    }


}
