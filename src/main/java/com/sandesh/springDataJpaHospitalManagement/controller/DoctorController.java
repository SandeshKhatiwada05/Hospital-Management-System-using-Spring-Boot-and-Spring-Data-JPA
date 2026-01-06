package com.sandesh.springDataJpaHospitalManagement.controller;

import com.sandesh.springDataJpaHospitalManagement.DTO.DoctorDTO;
import com.sandesh.springDataJpaHospitalManagement.entity.Doctor;
import com.sandesh.springDataJpaHospitalManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/insertDoctor")
    public Doctor insertDoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorService.saveDoctorInfo(doctorDTO);
    }

    @GetMapping("/viewDoctors")
    public List<Doctor> viewDoctors(){
        return doctorService.viewDoctors();
    }
}
