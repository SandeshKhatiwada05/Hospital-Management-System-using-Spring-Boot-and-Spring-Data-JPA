package com.sandesh.springDataJpaHospitalManagement;

import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
public class PatientTest {


    private final PatientRepository patientRepository;

}
