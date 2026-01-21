package com.sandesh.springDataJpaHospitalManagement.controller;

import com.sandesh.springDataJpaHospitalManagement.DTO.LoginRequestDTO;
import com.sandesh.springDataJpaHospitalManagement.DTO.LoginResponseDTO;
import com.sandesh.springDataJpaHospitalManagement.DTO.SignupResponseDTO;
import com.sandesh.springDataJpaHospitalManagement.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO dto
    ) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(
            @RequestBody LoginRequestDTO dto
    ) {
        return ResponseEntity.ok(authService.signup(dto));
    }
}
