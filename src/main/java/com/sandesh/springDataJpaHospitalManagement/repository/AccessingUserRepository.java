package com.sandesh.springDataJpaHospitalManagement.repository;

import com.sandesh.springDataJpaHospitalManagement.entity.AccessingUser;
import com.sandesh.springDataJpaHospitalManagement.entity.enumTypes.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AccessingUserRepository extends JpaRepository<AccessingUser, Long> {
    Optional<AccessingUser> findByUsername(String username);

    Optional<AccessingUser> findByProviderIdAndProviderType(String authProviderId, AuthProviderType authProviderType);
}