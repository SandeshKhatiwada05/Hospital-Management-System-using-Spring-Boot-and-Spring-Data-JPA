package com.sandesh.springDataJpaHospitalManagement.security;

import com.sandesh.springDataJpaHospitalManagement.DTO.LoginRequestDTO;
import com.sandesh.springDataJpaHospitalManagement.DTO.LoginResponseDTO;
import com.sandesh.springDataJpaHospitalManagement.DTO.SignupResponseDTO;
import com.sandesh.springDataJpaHospitalManagement.config.ApplicationConfig;
import com.sandesh.springDataJpaHospitalManagement.entity.AccessingUser;
import com.sandesh.springDataJpaHospitalManagement.repository.AccessingUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccessingUserRepository accessingUserRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final ApplicationConfig applicationConfig;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        AccessingUser user = (AccessingUser) authentication.getPrincipal();
        String token = authUtil.generateToken(user);

        return new LoginResponseDTO(token, user.getId());
    }

    public SignupResponseDTO signup(LoginRequestDTO dto) {
        if (accessingUserRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new BadCredentialsException("Username already exists");
        }

        AccessingUser user = accessingUserRepository.save(
                AccessingUser.builder()
                        .username(dto.getUsername())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .build()
        );

        return applicationConfig.modelMapper().map(user, SignupResponseDTO.class);
    }
}
