package com.sandesh.springDataJpaHospitalManagement;

import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.util.List;

@SpringBootTest
public class patientTestClass {
    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void allPatientsViewer(){
        List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);
    }
}
