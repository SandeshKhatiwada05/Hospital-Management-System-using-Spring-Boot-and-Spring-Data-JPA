package com.sandesh.springDataJpaHospitalManagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ApplicationConfig {

    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("sandesh")
                .password(passwordEncoder().encode("sandesh"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.withUsername("doctor")
                .password(passwordEncoder().encode("doctor"))
                .roles("DOCTOR")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
