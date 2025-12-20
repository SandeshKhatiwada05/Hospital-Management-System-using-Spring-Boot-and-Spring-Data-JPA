package com.sandesh.springDataJpaHospitalManagement.service;

import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service

public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient getPatientID(Long id) {
        Patient p1 = patientRepository.findById(id).orElse(null);
        Patient p2 = patientRepository.findById(id).orElse(null);
        return p1;
    }

}


