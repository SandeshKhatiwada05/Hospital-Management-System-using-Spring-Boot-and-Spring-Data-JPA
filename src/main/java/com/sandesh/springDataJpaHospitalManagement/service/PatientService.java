package com.sandesh.springDataJpaHospitalManagement.service;

import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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

}


