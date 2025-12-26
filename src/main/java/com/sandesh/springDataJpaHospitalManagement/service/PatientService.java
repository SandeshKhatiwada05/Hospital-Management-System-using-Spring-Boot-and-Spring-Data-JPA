package com.sandesh.springDataJpaHospitalManagement.service;

import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.entity.type.GenderType;
import com.sandesh.springDataJpaHospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Service

public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

//    @Transactional
    public Patient getPatientID(Long id) {
        Patient p1 = patientRepository.findById(id).orElse(null);
        Patient p2 = patientRepository.findById(id).orElse(null);
        System.out.println(p1 == p2);

        p1.setAge(23); //this is managed by transactional and doesnot need to be saved
//        patientRepository.save(p1);
        return p1;
    }

    public Patient getPatientName(String name) {
        Patient p = patientRepository.findByName(name);
        return p;
    }

    public List<Patient> getAllPatientsByGender(String gender) {
        List<Patient> patientByGender = patientRepository.findByGender(GenderType.valueOf(gender));
        return patientByGender;
    }

    public List<Patient> getAllPatientsByAge(int age) {
        List<Patient> patientByAge = patientRepository.findByAge(age);
        return patientByAge;
    }

    public List<Object[]> getAllGenderCount(){
        List<Object[]> list = patientRepository.findByGenderCount();
        return list;
    }
}


