package com.sandesh.springDataJpaHospitalManagement.DTO;

import com.sandesh.springDataJpaHospitalManagement.entity.enumTypes.BimaStatus;
import com.sandesh.springDataJpaHospitalManagement.entity.enumTypes.BimaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BimaDTO {
    @NotBlank
    private String policyNumber;

    @NotNull
    private BimaType bimaType;

    @NotNull
    private BimaStatus status;

    @NotNull
    private LocalDate endDate;

    @NotNull
    @Positive
    private BigDecimal bimaAmount;
}