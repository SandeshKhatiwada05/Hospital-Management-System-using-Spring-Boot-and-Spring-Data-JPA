package com.sandesh.springDataJpaHospitalManagement.security;

import com.sandesh.springDataJpaHospitalManagement.DTO.LoginRequestDTO;
import com.sandesh.springDataJpaHospitalManagement.DTO.LoginResponseDTO;
import com.sandesh.springDataJpaHospitalManagement.DTO.SignupResponseDTO;
import com.sandesh.springDataJpaHospitalManagement.entity.AccessingUser;
import com.sandesh.springDataJpaHospitalManagement.entity.enumTypes.AuthProviderType;
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

    private final AuthenticationManager authenticationManager;
    private final AccessingUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtil authUtil;

    // EMAIL LOGIN ONLY
    public LoginResponseDTO login(LoginRequestDTO dto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        AccessingUser user = (AccessingUser) authentication.getPrincipal();
        String token = authUtil.generateToken(user);

        return new LoginResponseDTO(token, user.getId());
    }

    // EMAIL SIGNUP ONLY - require password for email signups
    public SignupResponseDTO signup(LoginRequestDTO dto) {

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new BadCredentialsException("Username already exists");
        }

        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new BadCredentialsException("Password is required for email signup");
        }

        AccessingUser user = AccessingUser.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .providerType(AuthProviderType.EMAIL)
                .build();

        user = userRepository.save(user);

        return new SignupResponseDTO(user.getId(), user.getUsername());
    }

    // Create OAuth2 user (no password) — call this from your OAuth2LoginService
    public AccessingUser createOAuthUser(String username, String providerId, AuthProviderType providerType) {

        // if user exists, return it (or handle merge/update as needed)
        var existing = userRepository.findByUsername(username);
        if (existing.isPresent()) return existing.get();

        AccessingUser user = AccessingUser.builder()
                .username(username)
                .password(null) // OAuth2 users have no password
                .providerId(providerId)
                .providerType(providerType)
                .build();

        return userRepository.save(user);
    }
}
