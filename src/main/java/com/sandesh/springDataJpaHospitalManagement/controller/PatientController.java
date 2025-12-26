package com.sandesh.springDataJpaHospitalManagement.controller;

import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService  patientService;

    @GetMapping("/getPatient/{id}")
    public Patient getSinglePatient(@PathVariable Long id){
        Patient p1 = patientService.getPatientID(id);

        return p1;
    }

    @GetMapping("/getPatientName/{name}")
    public Patient getPatientName(@PathVariable String name){
        Patient patientbyName = patientService.getPatientName(name);
        return patientbyName;
    }
}
