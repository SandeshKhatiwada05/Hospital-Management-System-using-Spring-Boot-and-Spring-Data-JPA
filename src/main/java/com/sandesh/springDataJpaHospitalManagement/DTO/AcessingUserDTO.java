package com.sandesh.springDataJpaHospitalManagement.DTO;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class AcessingUserDTO {

    private Long id;
    private String username;
    private String password;
}
