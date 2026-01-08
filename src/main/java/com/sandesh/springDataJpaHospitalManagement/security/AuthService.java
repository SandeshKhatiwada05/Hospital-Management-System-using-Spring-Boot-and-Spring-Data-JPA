package com.sandesh.springDataJpaHospitalManagement.security;

import com.sandesh.springDataJpaHospitalManagement.DTO.LoginRequestDTO;
import com.sandesh.springDataJpaHospitalManagement.DTO.LoginResponseDTO;
import com.sandesh.springDataJpaHospitalManagement.DTO.SignupResponseDTO;
import com.sandesh.springDataJpaHospitalManagement.config.ApplicationConfig;
import com.sandesh.springDataJpaHospitalManagement.entity.AccessingUser;
import com.sandesh.springDataJpaHospitalManagement.repository.AccessingUserRepository;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccessingUserRepository accessingUserRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final ApplicationConfig applicationConfig;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );
        AccessingUser user = (AccessingUser) authentication.getPrincipal();

        String token = authUtil.generateToken(user);
        return new LoginResponseDTO(token, user.getId());
    }

    public SignupResponseDTO signup(LoginRequestDTO signupRequestDTO) {
        AccessingUser user = accessingUserRepository.findByUsername(signupRequestDTO.getUsername()).orElse(null);

        if (user != null) throw new RuntimeException("User already exists");

        user = accessingUserRepository.save(AccessingUser.builder().username(signupRequestDTO.getUsername()).build());
        return applicationConfig.modelMapper().map(user, SignupResponseDTO.class);
    }
}
