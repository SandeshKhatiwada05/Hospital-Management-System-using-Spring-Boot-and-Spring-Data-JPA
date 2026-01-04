package com.sandesh.springDataJpaHospitalManagement.controller;

import com.sandesh.springDataJpaHospitalManagement.DTO.BimaDTO;
import com.sandesh.springDataJpaHospitalManagement.entity.Bima;
import com.sandesh.springDataJpaHospitalManagement.service.BimaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class BimaController {
    private final BimaService bimaService;

    // Create a new Bima and attach it to existing patient identified by pid
    @PostMapping("/{pid}/bima")
    public ResponseEntity<Bima> createBimaForPatient(
            @PathVariable("pid") Long pid,
            @RequestBody @Valid BimaDTO dto) throws Throwable {

        Bima created = bimaService.createAndAttachBima(pid, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/bimas")
    public List<Bima> getAllBimas() {
        return bimaService.getAllBimas();
    }
}