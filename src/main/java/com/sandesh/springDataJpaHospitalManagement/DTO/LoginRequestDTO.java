package com.sandesh.springDataJpaHospitalManagement.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    private String username;
    private String password;
}
