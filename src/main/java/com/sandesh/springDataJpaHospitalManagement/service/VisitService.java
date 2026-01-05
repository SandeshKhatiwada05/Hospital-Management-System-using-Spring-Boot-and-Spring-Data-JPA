package com.sandesh.springDataJpaHospitalManagement.service;

import com.sandesh.springDataJpaHospitalManagement.DTO.VisitDTO;
import com.sandesh.springDataJpaHospitalManagement.config.MapperConfig;
import com.sandesh.springDataJpaHospitalManagement.entity.Doctor;
import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.entity.Visit;
import com.sandesh.springDataJpaHospitalManagement.repository.DoctorRepository;
import com.sandesh.springDataJpaHospitalManagement.repository.PatientRepository;
import com.sandesh.springDataJpaHospitalManagement.repository.VisitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final MapperConfig modelMapperConfig;


    @Transactional
    public Patient insertVisting(VisitDTO visitDTO, Long doctorId, Long patientId) throws Throwable {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        Patient patient = (Patient) patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        Visit visit = modelMapperConfig.modelMapper().map(visitDTO, Visit.class);

        if (visit.getVisitId() != null)
            throw new IllegalArgumentException("Such data already present");

        if (patient.getVisits() == null)
            patient.setVisits(new ArrayList<>());

        patient.getVisits().add(visit);  // Add visit to the list
        visit.setPatient(patient);
        visit.setDoctor(doctor);

        visitRepository.save(visit);  // ensure it persists

        return patient;
    }

}
