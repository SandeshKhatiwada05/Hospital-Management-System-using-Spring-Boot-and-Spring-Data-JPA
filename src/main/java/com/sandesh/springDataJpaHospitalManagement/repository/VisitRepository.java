package com.sandesh.springDataJpaHospitalManagement.repository;

import com.sandesh.springDataJpaHospitalManagement.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}