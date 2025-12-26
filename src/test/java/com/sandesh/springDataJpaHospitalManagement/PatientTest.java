package com.sandesh.springDataJpaHospitalManagement;

import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.repository.PatientRepository;
import com.sandesh.springDataJpaHospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootTest
@RequiredArgsConstructor
public class PatientTest {

    private final PatientService patientService;
    private final PatientRepository patientRepository;

    public Patient getPatientName(String name){
        Patient patientbyName = patientService.getPatientName(name);
        return patientbyName;
    }
}
