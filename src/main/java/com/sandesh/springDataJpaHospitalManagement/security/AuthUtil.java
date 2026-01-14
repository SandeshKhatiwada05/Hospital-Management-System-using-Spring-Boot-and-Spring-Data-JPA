package com.sandesh.springDataJpaHospitalManagement.security;

import com.sandesh.springDataJpaHospitalManagement.entity.AccessingUser;
import com.sandesh.springDataJpaHospitalManagement.entity.enumTypes.AuthProviderType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class AuthUtil {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(AccessingUser user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSecretKey())
                .compact();
    }

    public String extractUsername(String jwtToken) {
        Claims payload = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        return payload.getSubject();
    }

    public AuthProviderType getProviderTypeFromRegistrationId(String registrationId) {
        return switch (registrationId.toLowerCase()) {
            case "google" -> AuthProviderType.GOOGLE;
            case "github" -> AuthProviderType.GITHUB;
            case "facebook" -> AuthProviderType.FACEBOOK;
            case "linkedin" -> AuthProviderType.LINKEDIN;
            case "twitter" -> AuthProviderType.TWITTER;
            default -> throw new IllegalArgumentException("Unsupported registration ID: " + registrationId);
        };
    }

    public String findByProviderIdFromOAuth2User(OAuth2User oAuth2User, String registrationId) {
        String providerId = switch (registrationId.toLowerCase()) {
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> String.valueOf(oAuth2User.getAttribute("id"));
            case "facebook" -> oAuth2User.getAttribute("id");
            case "linkedin" -> oAuth2User.getAttribute("id");
            case "twitter" -> String.valueOf(oAuth2User.getAttribute("id"));
            default -> {
                log.error("Unsupported registration ID: {}", registrationId);
                throw new IllegalArgumentException("Unsupported registration ID: " + registrationId);
            }
        };

        if (providerId == null || providerId.isEmpty()) {
            log.error("Provider ID not found in OAuth2User for registration ID: {}", registrationId);
            throw new IllegalArgumentException("Provider ID not found in OAuth2User for registration ID: " + registrationId);
        }
        return providerId;
    }

    public String determineUsernameFromOAuth2User(OAuth2User oAuth2User, String registrationId, String authProviderId) {
        String email = oAuth2User.getAttribute("email");
        if (email != null && !email.isEmpty()) {
            return email;
        } else {
            return switch (registrationId.toLowerCase()){
                case "google" -> oAuth2User.getAttribute("sub");
                case "github" -> oAuth2User.getAttribute("login");
                default -> authProviderId;
            };
        }
    }
}


