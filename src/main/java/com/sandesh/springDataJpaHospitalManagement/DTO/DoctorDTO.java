package com.sandesh.springDataJpaHospitalManagement.DTO;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DoctorDTO {

    private String doctorName;
    private String doctorEmail;
    private String specialization;

}
