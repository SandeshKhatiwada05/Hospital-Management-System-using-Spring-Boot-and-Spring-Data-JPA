package com.sandesh.springDataJpaHospitalManagement.entity;

import com.sandesh.springDataJpaHospitalManagement.entity.enumTypes.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(
        name = "bima_db",
        uniqueConstraints = @UniqueConstraint(columnNames = {"policy_number"})
)
public class Bima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bimaId;

    @Column(nullable = false)
    String policyNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    BimaType bimaType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    BimaStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDate startDate;

    @Column(nullable = false)
    LocalDate endDate;

    @Column(nullable = false)
    BigDecimal bimaAmount;

}
