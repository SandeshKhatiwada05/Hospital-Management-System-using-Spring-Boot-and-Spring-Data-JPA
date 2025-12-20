package com.sandesh.springDataJpaHospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(
        name = "Patient_Db",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_patient_name", columnNames = {"phoneNumber"}),
                //sandesh with gmail kh@gmail.com cannot be created two times
                @UniqueConstraint(name = "unique_email_phone", columnNames = {"name", "email"})
        },
        indexes = {
                @Index(name = "indx_name", columnList = "name")
        }
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dob;
    private String gender;
    private String email;
    private Long phoneNumber;
    private int age;
}
