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
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final MapperConfig modelMapperConfig;
    @Transactional
    public Patient insertVisting(VisitDTO visitDTO, Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = (Patient) patientRepository.findById(patientId).orElseThrow();

        Visit visit = modelMapperConfig.modelMapper().map(visitDTO, Visit.class);
        if (visit.getVisitId() != null) throw new IllegalArgumentException("Such data already present");
        patient.setVisits((List<Visit>) visit);
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        return patient;
    }
}
