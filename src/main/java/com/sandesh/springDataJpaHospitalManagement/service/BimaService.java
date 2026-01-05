package com.sandesh.springDataJpaHospitalManagement.service;

import com.sandesh.springDataJpaHospitalManagement.DTO.BimaDTO;
import com.sandesh.springDataJpaHospitalManagement.entity.Bima;
import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.repository.BimaRepository;
import com.sandesh.springDataJpaHospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BimaService {
    private final PatientRepository patientRepository;
    private final BimaRepository bimaRepository;

    @Transactional
    public Bima createAndAttachBima(Long patientId, BimaDTO dto) throws Throwable {
        Patient patient = (Patient) patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        Bima bima = new Bima();
        bima.setPolicyNumber(dto.getPolicyNumber());
        bima.setBimaType(dto.getBimaType());
        bima.setStatus(dto.getStatus());
        bima.setEndDate(dto.getEndDate());
        bima.setBimaAmount(dto.getBimaAmount());

        // attach both sides
        patient.setBima(bima);
        bima.setPatient(patient);

        // save owning side (Patient) - cascade = ALL will persist Bima
        patientRepository.save(patient);

        // return managed/persisted bima
        return patient.getBima();
    }

    public List<Bima> getAllBimas() {
        return bimaRepository.findAll();
    }

    public Patient removeBimaFromPatient(Long patientId) {
        Patient patient = (Patient) patientRepository.findById(patientId).orElseThrow();
        patient.setBima(null);
        return patient;
    }
}