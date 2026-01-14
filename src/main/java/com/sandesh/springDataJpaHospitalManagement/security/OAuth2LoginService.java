package com.sandesh.springDataJpaHospitalManagement.security;

import com.sandesh.springDataJpaHospitalManagement.DTO.LoginResponseDTO;
import com.sandesh.springDataJpaHospitalManagement.entity.AccessingUser;
import com.sandesh.springDataJpaHospitalManagement.entity.enumTypes.AuthProviderType;
import com.sandesh.springDataJpaHospitalManagement.repository.AccessingUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2LoginService {

    private final AccessingUserRepository userRepository;
    private final AuthUtil authUtil;

    @Transactional
    public ResponseEntity<LoginResponseDTO> handleOAuth2Login(
            OAuth2User oAuth2User,
            String registrationId
    ) {

        AuthProviderType providerType =
                authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId =
                authUtil.findByProviderIdFromOAuth2User(oAuth2User, registrationId);

        AccessingUser user =
                userRepository.findByProviderIdAndProviderType(providerId, providerType)
                        .orElse(null);

        String email = oAuth2User.getAttribute("email");

        if (user == null) {
            if (email != null && userRepository.findByUsername(email).isPresent()) {
                throw new BadCredentialsException(
                        "Email already linked with another provider"
                );
            }

            String username =
                    authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);

            user = userRepository.save(
                    AccessingUser.builder()
                            .username(username)
                            .providerId(providerId)
                            .providerType(providerType)
                            .build()
            );
        }

        String token = authUtil.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(token, user.getId()));
    }
}
