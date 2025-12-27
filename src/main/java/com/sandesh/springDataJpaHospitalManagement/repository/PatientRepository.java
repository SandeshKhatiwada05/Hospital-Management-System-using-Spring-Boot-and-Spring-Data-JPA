package com.sandesh.springDataJpaHospitalManagement.repository;

import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import java.util.*;

import com.sandesh.springDataJpaHospitalManagement.entity.type.GenderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByName(String name);
//    Patient findByEmailorPhoneNumber(String email, Long phone);

    @Query("SELECT p FROM Patient p WHERE p.gender = ?1")
    List<Patient> findByGender(@Param("gender") GenderType gender);

    @Query("SELECT p FROM Patient p WHERE p.age> :age") //easier approach
    List<Patient> findByAge(@Param("age")  int age);

    @Query("SELECT p.gender, COUNT(p) FROM Patient p GROUP BY p.gender")
    List<Object[]> findByGenderCount();

    @Query(value = "select * from Patient_Db", nativeQuery = true)
    List<Patient> findAllPatientsNativeQuery();
}
