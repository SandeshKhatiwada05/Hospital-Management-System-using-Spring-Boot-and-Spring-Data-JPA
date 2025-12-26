package com.sandesh.springDataJpaHospitalManagement.repository;

import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByName(String name);
//    Patient findByEmailorPhoneNumber(String email, Long phone);

    List<Patient> findByGender
}
