package com.sandesh.springDataJpaHospitalManagement.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponseDTO {

    String jsonWebToken;
    Long id;

}
