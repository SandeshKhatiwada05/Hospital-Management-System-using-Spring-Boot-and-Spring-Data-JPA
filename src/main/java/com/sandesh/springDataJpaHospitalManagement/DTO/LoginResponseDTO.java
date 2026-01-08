package com.sandesh.springDataJpaHospitalManagement.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    String jsonWebToken;
    Long id;

}
