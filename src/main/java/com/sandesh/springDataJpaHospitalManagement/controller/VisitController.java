package com.sandesh.springDataJpaHospitalManagement.controller;

import com.sandesh.springDataJpaHospitalManagement.DTO.VisitDTO;
import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping("/visitingService/{doctor_id}/{patient_id}")
    public Patient insertVisitOfPatient(@RequestBody VisitDTO visitDTO, @PathVariable Long doctor_id, @PathVariable Long patient_id) {
        return visitService.insertVisting(visitDTO, doctor_id, patient_id);
    }

}
