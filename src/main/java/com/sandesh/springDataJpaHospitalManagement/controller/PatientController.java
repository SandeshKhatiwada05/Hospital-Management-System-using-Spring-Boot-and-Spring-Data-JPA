package com.sandesh.springDataJpaHospitalManagement.controller;

import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/getPatientByGender/{gender}")
    public ArrayList<Patient> getPatientByGender(@PathVariable String gender){
        List<Patient> patientList = patientService.getAllPatientsByGender(gender);
        return new ArrayList<>(patientList);
    }

    @GetMapping("/getPatientAgeGreater/{age}")
    public ArrayList<Patient> getPatientAgeGreater(@PathVariable int age){
        List<Patient> patientList = patientService.getAllPatientsByAge(age);
        return new ArrayList<>(patientList);
    }

    @GetMapping("/listOfGenders")
    public List<Object[]> getListOfGenders(){
        List<Object[]> list = patientService.getAllGenderCount();
        return list;
    }

//    @GetMapping("/allPatientNativeQuery")
//    public List<Patient> getAllPatientNativeQuery(){
//        return patientService.getAllPatientByNativeQuery(PageRequest.of(0,2));
//    }

    @GetMapping("/allPatientNativeQuery")
    public ArrayList<Patient> getAllPatientNativeQuery(){
        List<Patient> patients = patientService.getAllPatientByNativeQuery();
        return new ArrayList<>(patients);
    }

    @PostMapping("/insertPatient")
    public Patient insertPatient(@RequestBody Patient patient){
        Patient patient1 = patientService.insertPatientInfo(patient);
        return patient1;
    }

}
