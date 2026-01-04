package com.sandesh.springDataJpaHospitalManagement.service;

import com.sandesh.springDataJpaHospitalManagement.entity.Bima;
import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.repository.BimaRepository;
import com.sandesh.springDataJpaHospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BimaService {
    private final PatientRepository patientRepository;
    private final BimaRepository bimaRepository;

    public void addBimaInfoToPatient(Bima bima, Long id) throws Throwable {
        Patient patient = (Patient) patientRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("Patient not found"));
        patient.setBima(bima);
        bima.setPatient(patient);


    }
}
