package com.sandesh.springDataJpaHospitalManagement.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandesh.springDataJpaHospitalManagement.DTO.LoginResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final OAuth2LoginService oAuth2LoginService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        OAuth2AuthenticationToken token =
                (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User =
                (OAuth2User) authentication.getPrincipal();

        ResponseEntity<LoginResponseDTO> result =
                oAuth2LoginService.handleOAuth2Login(
                        oAuth2User,
                        token.getAuthorizedClientRegistrationId()
                );

        response.setStatus(result.getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(
                objectMapper.writeValueAsString(result.getBody())
        );
    }
}
