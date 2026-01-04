package com.sandesh.springDataJpaHospitalManagement.repository;

import com.sandesh.springDataJpaHospitalManagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}