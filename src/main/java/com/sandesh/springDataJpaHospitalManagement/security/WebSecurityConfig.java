package com.sandesh.springDataJpaHospitalManagement.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionConfig ->
                        sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/swagger-ui/**", "/auth/**").permitAll() //just for swagger
                                .requestMatchers("/public/**").permitAll()
                                .requestMatchers("/login", "/login.html", "/css/**", "/js/**", "/images/**", "/webjars/**", "/favicon.ico").permitAll()
                                .anyRequest().authenticated()


//                     .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasRole("ADMIN") //just for swagger
//                     .requestMatchers("/doctor/**").hasAnyRole("DOCTOR", "ADMIN")
//                     .requestMatchers("/admin/**").hasRole("ADMIN")


                )
                .addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                // inside securityFilterChain(...)
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")                       // use your page
                        .failureHandler((request, response, exception) ->
                                log.error("OAuth2 Login failed: {}", exception.getMessage()))
                        .successHandler(oAuth2SuccessHandler)
                )
                .formLogin(form -> form
                        .loginPage("/login")                       // allow form login on same page
                        .permitAll()
                );
//                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}


