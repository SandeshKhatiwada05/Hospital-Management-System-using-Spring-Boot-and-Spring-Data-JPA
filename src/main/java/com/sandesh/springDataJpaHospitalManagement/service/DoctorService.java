package com.sandesh.springDataJpaHospitalManagement.service;

import com.sandesh.springDataJpaHospitalManagement.DTO.DoctorDTO;
import com.sandesh.springDataJpaHospitalManagement.config.ApplicationConfig;
import com.sandesh.springDataJpaHospitalManagement.entity.Doctor;
import com.sandesh.springDataJpaHospitalManagement.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final ApplicationConfig modelApplicationConfig;

    public Doctor saveDoctorInfo(DoctorDTO doctorDTO) {
        Doctor doctor = modelApplicationConfig.modelMapper().map(doctorDTO, Doctor.class);
        return doctorRepository.save(doctor);
    }

    public List<Doctor> viewDoctors(){
        return doctorRepository.findAll();
    }
}
