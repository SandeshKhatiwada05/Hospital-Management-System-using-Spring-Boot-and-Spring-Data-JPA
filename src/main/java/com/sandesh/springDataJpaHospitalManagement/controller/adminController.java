package com.sandesh.springDataJpaHospitalManagement.controller;

import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class adminController {
    private final PatientService patientService;


    @PostMapping("/insertPatient")
    public Patient insertPatient(@RequestBody Patient patient){
        Patient patient1 = patientService.insertPatientInfo(patient);
        return patient1;
    }

    @GetMapping("/patientWithBimaInformation")
    public List<Patient> getPatientWithBimaInformation(){
        return patientService.getPatientAndBimaInformation();
    }
}
